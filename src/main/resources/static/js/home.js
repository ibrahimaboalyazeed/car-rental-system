$.ajax({
  type: "GET",
  url: "",
  dataType: "json",
  headers: {
    Authorization: "Bearer " + document.cookie,
    Accept: "application/json",
  },
  success: function (response) {
    if (response.status_code == 200) {

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
}
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
      window.location.href = "car.html";
    },
    error: function (error) {
      console.error("Error:", error);
      $("#error-message").text("An error occurred during loading.");
    },
  });
}
//////////////////////////////////////////////////////////////////////////////////////////////////
