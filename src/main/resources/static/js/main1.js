function submitForm() {
  var formData = new FormData(document.getElementById("myForm"));

  sendDataToJavaScriptPage(formData);
}
