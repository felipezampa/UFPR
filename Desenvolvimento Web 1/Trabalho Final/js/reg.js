$(document).ready(function(){
    $("h1").on({
        mouseenter: function(){
            $(this).css("color", "rgb(184,2,2)");
        }
    });    
});

$(document).ready(function(){
    $("input").fadeToggle(5)
    $("p").slideToggle(5)
    $("input").fadeToggle(900)
    $("p").slideToggle(900)
});       
