// Captura do formulário
const form = document.getElementById("form-login");

form.addEventListener("submit", function (e) {
  e.preventDefault();

  const login = document.getElementById("login").value.trim();
  const senha = document.getElementById("senha").value.trim();

  if (!login || !senha) {
    alert("Preencha todos os campos.");
    return;
  }

  fetch("http://localhost:8080/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ login, senha })
  })
    .then(res => {
      if (!res.ok) throw new Error("Usuário ou senha inválidos");
      return res.json();
    })
    .then(data => {
      // Armazena usuário logado no sessionStorage
      sessionStorage.setItem("usuarioLogado", JSON.stringify(data));
      // Redireciona para a tela principal
      window.location.href = "home.html";
    })
    .catch(err => {
      alert("Erro ao fazer login: " + err.message);
    });
});



