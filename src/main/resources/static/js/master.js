function checkCredentials(event) {
  event.preventDefault();
  console.log("hi");
  var username = $("#username").val();
  var password = $("#password").val();
  console.log(username);

  // Send the data to the backend using AJAX
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/api/v1/auth/login",
    contentType: "application/json",
    data: JSON.stringify({ email: username, password: password }),
    dataType: "json",
    success: function (response) {
      console.log(response);

      if (response.status_code == 200) {
        document.cookie =
          "token=" +
          response.details.token +
          "; expires=Thu, 18 Dec 2023 12:00:00 UTC; path=/";
        // Get the token value from the "token" cookie
        const token = getCookie("token");

        // Now you can use the 'token' variable as needed
        console.log(token);

        window.location.href = "/index.html";
      } else if (response.status_code == 404) {
        // Display the error message in the error-message <div>
        $("#error-message").text(response.details[0]);
      }
    },
    error: function (error) {
      console.error("Error:", error);
      // Display a generic error message in case of AJAX failure
      $("#error-message").text("An error occurred during login.");
    },
  });
}
function getCookie(name) {
  const cookies = document.cookie.split(";");
  for (let i = 0; i < cookies.length; i++) {
    const cookie = cookies[i].trim();
    if (cookie.startsWith(name + "=")) {
      return cookie.substring(name.length + 1);
    }
  }
  return null;
}


function signUp(event) {
    event.preventDefault();
    console.log("hi");
    var email = $("#email").val();
    var password = $("#password").val();
    console.log(email);

    // Send the data to the backend using AJAX
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/v1/auth/signup",
        contentType: "application/json",
        data: JSON.stringify({ email: email, password: password }),
        dataType: "json",
        success: function (response) {
          console.log(response);
    
          if (response.status_code == 200) {
            document.cookie =
              "token=" +
              response.details.token +
              "; expires=Thu, 18 Dec 2023 12:00:00 UTC; path=/";
            // Get the token value from the "token" cookie
            const token = getCookie("token");
    
            // Now you can use the 'token' variable as needed
            console.log(token);
    
            window.location.href = "/index.html";
          } else if (response.status_code == 404) {
            // Display the error message in the error-message <div>
            $("#error-message").text(response.details[0]);
          }
        },
        error: function (error) {
          console.error("Error:", error);
          // Display a generic error message in case of AJAX failure
          $("#error-message").text("An error occurred during login.");
        },
    });
}


function bookingForm(event) {
  event.preventDefault();
  console.log("hi");
  var pick_up_location = $("#pick_up_location").val();
  var book_pick_date = $("#book_pick_date").val();
  var book_off_date = $("#book_off_date").val();
  var time_pick = $("#time_pick").val();
  var time_off = $("#time_off").val();

  // Combine date and time strings
  var startDateTimeString =
    convertDate(book_pick_date) + " " + convertTime(time_pick);
  var endDateTimeString =
    convertDate(book_off_date) + " " + convertTime(time_off);

  console.log("pick_up_location", pick_up_location);
  console.log("Start Date Time:", startDateTimeString);
  console.log("End Date Time:", endDateTimeString);

  //console.log("Start Date Time:", startDateTimeString);
  // console.log("End Date Time:", endDateTimeString);

  // Convert date strings to the format "MM/DD/YYYY" to "YYYY-MM-DD"
  function convertDate(dateString) {
    var parts = dateString.split("/");
    var year = parts[2];
    var month = parts[0].padStart(2, "0");
    var day = parts[1].padStart(2, "0");
    return `${year}-${month}-${day}`;
  }

  function convertTime(timeString) {
    var timeParts = timeString.match(/(\d+):(\d+)(\w+)/);

    if (!timeParts) {
      console.error("Invalid time format");
      return null; // Return null for invalid inputs
    }

    var hours = parseInt(timeParts[1], 10);
    var minutes = parseInt(timeParts[2], 10);
    var period = timeParts[3].toLowerCase();

    // Adjust hours for "pm"
    if (period === "pm" && hours !== 12) {
      hours += 12;
    }

    // Adjust hours for "am" at 12:00
    if (period === "am" && hours === 12) {
      hours = 0;
    }

    // Validate input values
    if (
      isNaN(hours) ||
      isNaN(minutes) ||
      hours < 0 ||
      hours > 23 ||
      minutes < 0 ||
      minutes > 59
    ) {
      console.error("Invalid time values");
      return null; // Return null for invalid inputs
    }

    return (
      hours.toString().padStart(2, "0") +
      ":" +
      minutes.toString().padStart(2, "0")
    ); // Format as HH:mm
  }
  const token = getCookie("token");
  var url =
    "http://localhost:8080/booking/available-cars?pickUpLocation=" +
    pick_up_location +
    "&startDateTime=" +
    startDateTimeString +
    "&endDateTime=" +
    endDateTimeString;

 $.ajax({
    type: "GET",
    url: url,
    dataType: "json",
    headers: {
        Authorization: "Bearer " + token,
        Accept: "application/json",
    },
    success: function (response) {
        console.log(response);

        if (response.status_code == 200) {
            var cars = response.details;

            // Assuming you have an element with the ID 'car-list-container'
            var carListContainer = $("#car-list-container");

            // Clear existing content (optional, depending on your needs)
            carListContainer.empty();

            // Iterate through the cars and append them to the HTML
            for (var i = 0; i < cars.length; i++) {
                var car = cars[i];

                // Create a new car element and append it to the container
                var carElement = '<div class="col-md-4">';
                carElement += '<div class="car-wrap rounded ftco-animate">';
                carElement += '<div class="img rounded d-flex align-items-end" style="background-image: url(images/car-1.jpg);"></div>';
                carElement += '<div class="text">';
                carElement += '<h2 class="mb-0"><a href="car-single.html">' + car.make + ' ' + car.model + '</a></h2>';
                carElement += '<div class="d-flex mb-3">';
                carElement += '<span class="cat">' + car.category.name + '</span>';
                carElement += '<p class="price ml-auto">' + car.pricePerDay + ' <span>/day</span></p>';
                carElement += '</div>';
                carElement += '<p class="d-flex mb-0 d-block"><a href="#" class="btn btn-primary py-2 mr-1">Book now</a> <a href="car-single.html" class="btn btn-secondary py-2 ml-1">Details</a></p>';
                carElement += '</div>';
                carElement += '</div>';
                carElement += '</div>';

                carListContainer.append(carElement);
            }

            // Redirect after a delay (900 milliseconds in your example)
            setTimeout(function () {
                window.location.href = "/car.html";
            }, 900);
        } else if (response.status_code == 404) {
            // Display the error message in the error-message <div>
            $("#error-message").text(response.details[0]);
        }
    },
    error: function (error) {
        console.error("Error:", error);
        // Display a generic error message in case of AJAX failure
        $("#error-message").text("An error occurred during login.");
    },
});

}

// function updateCarList(heroSection, cars) {
//   var carListContainer = $(".ftco-section.bg-light .container .row");

//   // Clear the existing content in both the car list and hero section
//   carListContainer.empty();
//   heroSection.empty();

//   // Construct the HTML for the hero section (assuming it remains the same for all cars)
//   var heroHtml = `
//     <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('images/bg_3.jpg');"
//       data-stellar-background-ratio="0.5">
//       <div class="overlay"></div>
//       <div class="container">
//         <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
//           <div class="col-md-9 ftco-animate pb-5">
//             <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i
//                   class="ion-ios-arrow-forward"></i></a></span> <span>Cars <i
//                 class="ion-ios-arrow-forward"></i></span></p>
//             <h1 class="mb-3 bread">Choose Your Car</h1>
//           </div>
//         </div>
//       </div>
//     </section>
//   `;

//   // Append the hero HTML to the hero section
//   heroSection.append(heroHtml);

//   // Iterate through the list of cars and append them to the HTML
//   for (var i = 0; i < cars.length; i++) {
//     var car = cars[i];

//     // Construct the HTML for each car
//     var carHtml = `
//       <div class="col-md-4">
//         <div class="car-wrap rounded ftco-animate">
//           <div class="img rounded d-flex align-items-end" style="background-image: url(images/car-${
//             i + 1
//           }.jpg);"></div>
//           <div class="text">
//             <h2 class="mb-0"><a href="car-single.html">${car.make} ${
//       car.model
//     }</a></h2>
//             <div class="d-flex mb-3">
//               <span class="cat">${car.category.name}</span>
//               <p class="price ml-auto">$${car.pricePerDay} <span>/day</span></p>
//             </div>
//             <p class="d-flex mb-0 d-block">
//               <a href="#" class="btn btn-primary py-2 mr-1">Book now</a>
//               <a href="car-single.html" class="btn btn-secondary py-2 ml-1">Details</a>
//             </p>
//           </div>
//         </div>
//       </div>
//     `;

//     // Append the car HTML to the car list container
//     carListContainer.append(carHtml);
//   }
// }
