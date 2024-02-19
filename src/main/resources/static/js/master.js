// check email and password function
function checkCredentials(event) {
  event.preventDefault();

  var username = $("#username").val();
  var password = $("#password").val();
  let userId;
  // Send the data to the backend using AJAX
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/api/v1/auth/login",
    contentType: "application/json",
    data: JSON.stringify({ email: username, password: password }),
    dataType: "json",
    success: function (response) {
      if (response.status_code == 200) {
        document.cookie = response.details.token;
        sessionStorage.setItem("userId", response.details.id);
        $.ajax({
          type: "GET",
          dataType: "json",
          url:
            "http://localhost:8080/clients/user/" +
            sessionStorage.getItem("userId"),
          headers: {
            Authorization: "Bearer " + document.cookie,
            Accept: "application/json",
          },
          success: function (response) {
            if (response.status_code == 200) {
              sessionStorage.setItem("clientId", response.details.id);
              sessionStorage.setItem("fullName", response.details.fullName);
              window.location.href = "/index.html";
            } else if (response.status_code == 404) {
              console.log(response);
              $("#error-message").text(response.details[0]);
            }
          },
          error: function (error) {
            console.error("Error:", error);
            // Display a generic error message in case of AJAX failure
            $("#error-message").text("An error occurred during login.");
          },
        });
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
////////////////////////////////////////////////////////////////////////////////////////////////
function signUp(event) {
  event.preventDefault();

  var email = $("#email").val();
  var password = $("#password").val();

  // Send the data to the backend using AJAX
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/api/v1/auth/signup",
    contentType: "application/json",
    data: JSON.stringify({ email: email, password: password }),
    dataType: "json",
    success: function (response) {
      if (response.status_code == 200) {
        sessionStorage.setItem("userId", response.details.id);
        document.cookie = response.details.token;
        window.location.href = "/client-form.html";
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
////////////////////////////////////////////////////////////////////////////////////////////////
function clientInfo(event) {
  event.preventDefault();
  let fullName = $("#fullName").val();
  let phoneNumber = $("#phoneNumber").val();
  let country = $("#country").val();
  let city = $("#city").val();
  let street = $("#street").val();
  let userId = sessionStorage.getItem("userId");

  $.ajax({
    type: "POST",
    url: "http://localhost:8080/clients/create",
    contentType: "application/json",
    dataType: "json",
    data: JSON.stringify({
      fullName: fullName,
      phoneNumber: phoneNumber,
      street: street,
      city: city,
      country: country,
      user: { id: userId },
    }),
    headers: {
      Authorization: "Bearer " + document.cookie,
      Accept: "application/json",
    },
    success: function (response) {
      if (response.status_code == 200) {
        sessionStorage.setItem("clientId", response.details.id);
        sessionStorage.setItem("fullName", response.details.fullName);
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
