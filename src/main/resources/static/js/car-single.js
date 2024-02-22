let urlParams = new URLSearchParams(window.location.search);
let carId = urlParams.get("carId");
console.log(carId);

$.ajax({
  type: "GET",
  url: "http://localhost:8080/cars/" + carId,
  dataType: "json",
  headers: {
    Authorization: "Bearer " + document.cookie,
    Accept: "application/json",
  },
  success: function (response) {
    if (response.status_code == 200) {
      console.log(response.details);
      carIfo(response.details);
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
function carIfo(car) {
  let targetElement = document.querySelector(".car-info");
  targetElement.innerHTML = `<div class="col-md-12">
					<div class="car-details">
						<div class="img rounded" style="background-image: url(images/${
              car.make + "-" + car.model
            }.jpg);"></div>
						<div class="text text-center">
							<span class="subheading">${car.model}</span>
							<h2>${car.make + " " + car.model}</h2>
						</div>
					</div>
				</div>`;
  $("#feat div #mileage").text(`${car.mileage}`);
  $("#feat div #seats").text(`${car.seats} Adults`);
  $("#feat div #bags").text(`${car.smallBag} Bags`);
  $("#feat div #trans").text(`${car.transmission}`);
}
