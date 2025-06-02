const menuToggle = document.getElementById('menuToggle');
const sidebar = document.getElementById('sidebar');


menuToggle.addEventListener('click', () => {
  sidebar.classList.toggle('active');
});

document.addEventListener("DOMContentLoaded", () => {
  const btnLogout = document.getElementById("btn_logoff");

  if (btnLogout) {
    btnLogout.addEventListener("click", () => {
      sessionStorage.removeItem("usuarioLogado"); // remove os dados de login
      window.location.href = "login.html"; // redireciona para login
    });
  }
});
