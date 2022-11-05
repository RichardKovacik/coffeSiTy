const regex = new RegExp('^[a-zA-Z]*$');

function validateForm() {
    if (validateLastName() && validateName() && validateEmail()){
        return true;
    }
    return false;
}

function validateName() {
   var name = document.getElementById("fn").value;
   var nameError = document.getElementById("nameErr");
   if(name.length < 2) {
       nameError.innerHTML = "Meno musi obsahovat aspon 2 znaky";
       return false;
   }else if(!regex.test(name)) {
       nameError.innerHTML = "Meno musi obsahovat pismena z abecedy.";
       return false;
   } else {
       nameError.innerHTML = "";
       return true;
   }
}
function validateLastName() {
    var lastName = document.getElementById("ln").value;
    var lnErr = document.getElementById("lnErr");
    if(lastName.length < 2) {
        lnErr.innerHTML = "Priezvisko musi obsahovat aspon 2 znaky";
        return false;
    }else if(!regex.test(lastName)) {
        lnErr.innerHTML = "Priezvisko musi obsahovat pismena z abecedy.";
        return false;
    } else {
        lnErr.innerHTML = "";
        return true;
    }
}
function validateEmail() {
    var email = document.getElementById("email").value;
    var emailErr = document.getElementById("emailErr");
    var re = /\S+@\S+\.\S+/;
    //email musi byt v tvare string@string.string

    if(email.length < 3) {
        emailErr.innerHTML = "Email musi obsahovat aspon 3 znaky";
        return false;
    }else if (!re.test(email)) {
        emailErr.innerHTML = "Email je v neplatnom tvare.";
        return false;
    } else {
        emailErr.innerHTML = "";
        return true;
    }
}