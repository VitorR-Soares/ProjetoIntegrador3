const usuarioLogado = sessionStorage.getItem("usuarioLogado");

if (!usuarioLogado) {
  window.location.href = "login.html"; // redireciona se não estiver logado
}

// === Coleta dos elementos do DOM ===
const form = document.querySelector(".user-form");
const inputUsername = document.getElementById("input_username");
const inputSenha = document.getElementById("input_senha");
const inputCargo = document.getElementById("input_cargo");

// === Função para preencher o select de Cargos ===
const preencherCargos = () => {
  fetch('http://localhost:8080/cargo')
    .then(response => {
      if (!response.ok) {
        throw new Error("Erro ao buscar cargos");
      }
      return response.json();
    })
    .then(cargos => {
      cargos.forEach(cargo => {
        const option = document.createElement("option");
        option.value = cargo.id;
        option.textContent = cargo.nome_cargo;
        inputCargo.appendChild(option);
      });
    })
    .catch(error => {
      console.error("Erro ao carregar cargos:", error);
      alert("Não foi possível carregar os cargos. Verifique o servidor.");
    });
};

// === Evento de submit para cadastrar usuário ===
form.addEventListener("submit", (event) => {
  event.preventDefault();

  const username = inputUsername.value.trim();
  const senha = inputSenha.value.trim();
  const idCargo = inputCargo.value;

  if (!username || !senha || !idCargo) {
    alert("Preencha todos os campos antes de cadastrar.");
    return;
  }

  const novoUsuario = {
    nome_usuario: username,
    senha: senha,
    cargo: {
      id: idCargo
    }
  };

  fetch('http://localhost:8080/usuario', { 
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(novoUsuario)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Erro ao cadastrar usuário");
      }
      return response.json();
    })
    .then(data => {
      alert("Usuário cadastrado com sucesso!");
      window.location.href = "gestao_cadastros.html";
    })
    .catch(error => {
      console.error("Erro ao cadastrar usuário:", error);
      alert("Erro ao cadastrar usuário. Verifique os dados ou o servidor.");
    });
});

// === Chamada inicial para preencher os cargos ===
preencherCargos();



