$.ajax({
  type: "GET",
  url: "http://localhost:8080/cars/featured",
  dataType: "json",
  headers: {
    Authorization: "Bearer " + document.cookie,
    Accept: "application/json",
  },
  success: function (response) {
    if (response.status_code == 200) {
      console.log(response.details);
      addFeaturedCars(response.details);
    } else if (response.status_code == 404) {
      // Display the error message in the error-message <div>
      $("#error-message").text(response.details[0]);
    }
  },
  error: function (error) {
    console.error("Error:", error);
    $("#error-message").text("An error occurred during loading.");
  },
});
////////////////////////////////////////////////////////////////////////
function bookingForm(event) {
  event.preventDefault();
  let pick_up_location = $("#pick_up_location").val();
  let pickDate = $("#book_pick_date").val();
  let offDate = $("#book_off_date").val();
  let timePick = $("#time_pick").val();
  let timeOff = $("#time_off").val();

  let pickyear = pickDate.slice(0, 4);
  let pickmonth = pickDate.slice(4, 6);
  let pickday = pickDate.slice(6, 8);
  let finalPickDate = pickyear + "-" + pickmonth + "-" + pickday;
  let dropyear = offDate.slice(0, 4);
  let dropmonth = offDate.slice(4, 6);
  let dropday = offDate.slice(6, 8);
  let finalDropDate = dropyear + "-" + dropmonth + "-" + dropday;

  let startDateTime = finalPickDate + " " + timePick;
  let endDateTime = finalDropDate + " " + timeOff;

  let url =
    "http://localhost:8080/booking/available-cars?pickUpLocation=" +
    pick_up_location +
    "&startDateTime=" +
    startDateTime +
    "&endDateTime=" +
    endDateTime;

  $.ajax({
    type: "GET",
    url: url,
    dataType: "json",
    headers: {
      Authorization: "Bearer " + document.cookie,
      Accept: "application/json",
    },
    success: function (response) {
      sessionStorage.setItem("pick_up_location", pick_up_location);
      sessionStorage.setItem("startDateTime", startDateTime);
      sessionStorage.setItem("endDateTime", endDateTime);
      window.location.href = "available-cars.html";
    },
    error: function (error) {
      console.error("Error:", error);
      $("#error-message").text("An error occurred during loading.");
    },
  });
}
//////////////////////////////////////////////////////////////////////////////////////////////////
function addFeaturedCars(cars) {
  $(".item #img1").attr(
    "style",
    `background-image: url(images/${cars[0].make + "-" + cars[0].model}.jpg);`
  );
  $(".item #img2").attr(
    "style",
    `background-image: url(images/${cars[1].make + "-" + cars[1].model}.jpg);`
  );
  $(".item #img3").attr(
    "style",
    `background-image: url(images/${cars[2].make + "-" + cars[2].model}.jpg);`
  );
  $(".item #img4").attr(
    "style",
    `background-image: url(images/${cars[3].make + "-" + cars[3].model}.jpg);`
  );

  $(".text h2 #car-1").text(`${cars[0].make}  ${cars[0].model}`);
  $(".text h2 #car-2").text(`${cars[1].make}  ${cars[1].model}`);
  $(".text h2 #car-3").text(`${cars[2].make}  ${cars[2].model}`);
  $(".text h2 #car-4").text(`${cars[3].make}  ${cars[3].model}`);

  $(".text h2 #car-1").attr("car-id", `${cars[0].id}`);
  $(".text h2 #car-2").attr("car-id", `${cars[1].id}`);
  $(".text h2 #car-3").attr("car-id", `${cars[2].id}`);
  $(".text h2 #car-4").attr("car-id", `${cars[3].id}`);

  $(".text div #cat-1").text(`${cars[0].make}`);
  $(".text div #cat-2").text(`${cars[1].make}`);
  $(".text div #cat-3").text(`${cars[2].make}`);
  $(".text div #cat-4").text(`${cars[3].make}`);

  $(".text div #p1").prepend(`${cars[0].pricePerDay}`);
  $(".text div #p2").prepend(`${cars[1].pricePerDay}`);
  $(".text div #p3").prepend(`${cars[2].pricePerDay}`);
  $(".text div #p4").prepend(`${cars[3].pricePerDay}`);
}
////////////////////////////////////////////////////////////
let detailsBtns = document.querySelectorAll(".details");

detailsBtns.forEach((btn) => {
  btn.addEventListener("click", function () {
    // Find the closest ancestor element with the class "item"
    let itemElement = this.closest(".item");
    let carId = itemElement.querySelector(`.mb-0 a`).getAttribute("car-id");
    console.log(carId);
    let carPage = `/car-single.html?carId=${carId}`;
    window.location.href = carPage;
  });
});
////////////////////////////////////////////////////////////
let bookBtns = document.querySelectorAll(".book");

bookBtns.forEach((btn) => {
  btn.addEventListener("click", function (e) {
    e.target.classList.remove("btn-primary");
    e.target.textContent = "Reserved ✓";
    e.target.classList.add("btn-danger");
    e.target.classList.add("text-dark");
  });
});
