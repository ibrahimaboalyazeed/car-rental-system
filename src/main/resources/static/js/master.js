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

function submitForm(event) {
  event.preventDefault();
  console.log("hi");
  var book_pick_date = $("#book_pick_date").val();
  var book_off_date = $("#book_off_date").val();
  var time_pick = $("#time_pick").val();
  var time_off = $("#time_off").val();

  // Combine date and time strings
  var startDateTimeString =
    convertDate(book_pick_date) + " " + convertTime(time_pick);
  var endDateTimeString =
    convertDate(book_off_date) + " " + convertTime(time_off);

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
    "http://localhost:8080/booking/available-cars?startDateTime=" +
    startDateTimeString +
    "&endDateTime=" +
    endDateTimeString;

  // Send the data to the backend using AJAX
  $.ajax({
    type: "GET",
    url: url,
    dataType: "json",
    headers: {
      Authorization: "Bearer " + token,
    },
    success: function (response) {
      console.log(response);

      if (response.status_code == 200) {
        window.location.href = "/car.html";
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
