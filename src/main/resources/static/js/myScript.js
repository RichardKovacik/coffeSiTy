const regex = new RegExp('^[a-zA-Z]*$');

function validateForm() {
    if (validateLastName() && validateName() && validateEmail() && validateNick()
        && validateBirthDate() && validatePass() && validatePSC() && validateStreet()){
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

function validateNick() {
    var nick = document.getElementById("nick").value;
    var nickErr = document.getElementById("nickErr");
    var re = /^[a-zA-Z0-9]+$/;
    //pouzivatelkse meno moze obsahovat len cisla alebo pismena napr jaro12, 11riso

    if(nick.length < 3) {
        nickErr.innerHTML = "Pouzivatelske meno musi obsahovat aspon 3 znaky.";
        return false;
    }else if (!re.test(nick)) {
        nickErr.innerHTML = "Pouzivatelske meno musi obsahovat len pismena a cisla alebo ich kombinaciu.";
        return false;
    } else {
        nickErr.innerHTML = "";
        return true;
    }
}

function validateBirthDate() {
    var bdValue = document.getElementById("date").value;
    var dateErr = document.getElementById("dateErr");
    var today = new Date();
    var bd = new Date(bdValue);
    let yearsDiff = today.getFullYear() - bd.getFullYear();
    // console.log("today"+today.getFullYear());
    // console.log("bd: "+bd.getFullYear());
    // console.log("lenght: "+bdValue.length);
    if (bdValue.length > 10) {
        dateErr.innerHTML = "Neplatny datum narodenia.";
        return false;
    } else if (bd >= today) {
        dateErr.innerHTML = "Neplatny datum narodenia.";
        return false;
    } else if (yearsDiff < 18 || yearsDiff > 100) {
        dateErr.innerHTML = "Neplatny datum narodenia, uzivatel nesmie byt starsi ako 100 rokov a zroven musi mat viac ako 18.";
        return false;
    } else
        dateErr.innerHTML = "";
    return true;
}

function validatePass() {
    var passValue = document.getElementById("pass").value;
    var passErr = document.getElementById("passErr");

    if(passValue.length < 3) {
        passErr.innerHTML = "Heslo meno musi obsahovat aspon 3 znaky.";
        return false;
    }else if (passValue.length > 30) {
        passErr.innerHTML = "Heslo moze obsahovat max 30 znakov.";
        return false;
    } else {
        passErr.innerHTML = "";
        return true;
    }
}
function validatePSC() {
    var pscValue = document.getElementById("psc").value;
    var pscErr = document.getElementById("pscErr");
    var re = /[0-9]+/;

    if(pscValue.length < 5) {
        pscErr.innerHTML = "PSC musi obsahovat prave 5 znakov";
        return false;
    }else if (!re.test(pscValue)) {
        pscErr.innerHTML = "PSC musi obsahovat len cisla";
        return false;
    } else {
        pscErr.innerHTML = "";
        return true;
    }
}

//zatial len pre jednoduchost pocet znakov pre ulicu
function validateStreet() {
    var strettValue = document.getElementById("street").value;
    var streetErr = document.getElementById("streetErr");
    //todo: regex ulica a cislo napr. Hviezdoslavova 72

    if(strettValue.length < 4) {
        streetErr.innerHTML = "Nazov ulice musi obsahovat aspon 4 znaky";
        return false;
    }else if (strettValue.length > 30) {
        streetErr.innerHTML = "Nazov ulice moze osahovat max 30 znakov";
        return false;
    } else {
        streetErr.innerHTML = "";
        return true;
    }
}
