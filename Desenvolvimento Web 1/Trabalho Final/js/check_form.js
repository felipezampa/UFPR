$(function(){
  $("#form-test").on("submit",function(){
    nome_input = $("input[name='name']");

    if(nome_input.val() == "" || nome_input.val() == null)
    {
      alert("Preencha o nome.");
      return(false);
    }

    return(true);
  });
});

$(function(){
  $("#form-test").on("submit",function(){
    email_input = $("input[email='email']");

    if(email_input.val() == "" || email_input.val() == null)
    {
      alert("Preencha o E-Mail.");
      return(false);
    }

    return(true);
  });
});

$(function(){
  $("#form-test").on("submit",function(){
    nasc_input = $("input[nasc='nasc']");

    if(nasc_input.val() == "" || nasc_input.val() == null)
    {
      alert("Preencha a Data de Nascimento.");
      return(false);
    }

    return(true);
  });
});

$(function(){
  $("#form-test").on("submit",function(){
    password_input = $("input[password='password']");

    if(password_input.val() == "" || password_input.val() == null)
    {
      alert("Preencha a Senha.");
      return(false);
    }

    return(true);
  });
});

$(function(){
  $("#form-test").on("submit",function(){
    confirmpass_input = $("input[confirmpass='confirmpass']");

    if(confirmpass_input.val() == "" || confirmpass_input.val() == null)
    {
      alert("Preencha o campo de Confirmar a Senha.");
      return(false);
    }

    return(true);
  });
});
;