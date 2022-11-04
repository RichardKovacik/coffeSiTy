function validateName() {
   var name = document.getElementById("fn").value;
   var nameError = document.getElementById("nameErr");
   if(name.length < 2) {
       nameError.innerHTML = "Meno musi obsahovat aspon 2 znaky";
       return false;
   }else {
       nameError.innerHTML = "";
       return true;
   }
}
