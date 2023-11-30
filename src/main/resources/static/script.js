// Función para mostrar el menú
function showMenu() {
    // Obtenemos el elemento del menú
    var menu = document.querySelector(".menu");
  
    // Establecemos la altura del menú a 100px
    menu.style.height = "100px";
  }
  
  // Función para ocultar el menú
  function hideMenu() {
    // Obtenemos el elemento del menú
    var menu = document.querySelector(".menu");
  
    // Establecemos la altura del menú a 0px
    menu.style.height = "0px";
  }
  
  // Escuchamos el evento click del elemento del menú hamburguesa
  document.querySelector(".hamburger-menu").addEventListener("click", showMenu);
  
  // Escuchamos el evento click del elemento del menú
  document.querySelector(".menu").addEventListener("click", hideMenu);
  