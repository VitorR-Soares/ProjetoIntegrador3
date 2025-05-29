// ====================
// COLETA DE ELEMENTOS
// ====================
const formCliente = document.querySelector(".client-form");
const inputNome = document.getElementById("input_nome");
const inputCpf = document.getElementById("input_cpf");
const inputTelefone = document.getElementById("input_telefone");
const inputIdade = document.getElementById("input_idade");
const radioGenero = document.getElementsByName("genero");

// ================
// SUBMIT DO FORMULÁRIO
// ================
formCliente.addEventListener("submit", function (event) {
  
  event.preventDefault();
  console.log("Teste 1");

  // Coleta o valor do gênero selecionado
  let generoSelecionado = "";
  radioGenero.forEach(radio => {
    if (radio.checked) {
      generoSelecionado = radio.nextSibling.textContent.trim(); // "Masculino" ou "Feminino"
    }
  });
  
  console.log("Teste 2");
  // Cria objeto com os dados do cliente
  const novoCliente = {
    nome_cliente: inputNome.value.trim(),
    cpf: inputCpf.value.trim(),
    telefone: inputTelefone.value.trim(),
    idade: parseInt(inputIdade.value.trim()),
    genero: generoSelecionado
  };
  console.log(novoCliente);
  // =================
  // ENVIO PARA A API
  // =================
  fetch("http://localhost:8080/cliente", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(novoCliente)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Erro ao cadastrar cliente");
      }
      return response.json();
    })
    .then(data => {
      console.log("Cliente cadastrado com sucesso:", data);
      window.location.href = "gestao_cadastros.html";
    })
    .catch(error => {
      console.error("Erro no cadastro:", error);
      alert("Ocorreu um erro ao cadastrar o cliente. Verifique os dados ou o servidor.");
    });
});
