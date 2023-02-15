var form = document.querySelector("form");
form.addEventListener("submit", function (e) {
  e.preventDefault(); //para o submit
  const ballClasses = document.querySelectorAll(".bolas nav");
  const formDiv = document.querySelector("#form-class");
  
  const formData = new URLSearchParams(new FormData(form)).toString();
  fetch('AuthServlet?action=salvarConta&' + formData, {
    method: 'POST',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
  });

  addClasse(ballClasses[2], "ball-next");
  setDisplayNone("signin_confirm");
  setDisplayNone("signin_button");
  setDisplayFlex("create_sucess");
  setDisplayNone("email_senha");
  setDisplayNone("alterText");
  addClasse(formDiv, "form-class");
  console.log("ab", ballClasses);
});

function setDisplayNone(id) {
  document.getElementById(id).style.display = "none";
}
function setDisplayFlex(id) {
  document.getElementById(id).style.display = "flex";
}
function alterText(id, text) {
  document.getElementById(id).textContent = text;
}
function addClasse(elemento, newClass) {
  elemento.classList.add(newClass);
}
