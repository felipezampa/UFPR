$(document).ready(function() {
        $('.recuperar').click(function() {
            if (!$('#email').val()) {
                alert('Digite seu email!');
            }
            else{
              alert("Um email foi enviado com as instruções de recuperação de conta."); 
              window.location.href = "index.php";
            }
        })
    });

