const usuarioLogado = sessionStorage.getItem("usuarioLogado");

if (!usuarioLogado) {
  window.location.href = "login.html"; // redireciona se não estiver logado
}

// 1) Captura dos elementos do DOM
const inputDescricao = document.getElementById("input_descricao");
const inputValor = document.getElementById("input_valor");
const inputData = document.getElementById("input_data");
const inputDataEntrega = document.getElementById("input_data_entrega");
const inputUsuario = document.getElementById("input_usuario");
const inputPagamento = document.getElementById("input_pagamento");
const inputCliente = document.getElementById("input_cliente");
const inputDesconto = document.getElementById("input_desconto");

const totalItensSpan = document.getElementById("total-itens");
const valorTotalSpan = document.getElementById("valor-total");
const totalDescontosSpan = document.getElementById("total-descontos");
const valorFinalSpan = document.getElementById("valor-final");

const btnAplicarDesconto = document.querySelector(".aplicar-btn");
const form = document.querySelector(".order-form");

let descontoPagamento = 0;

// 2) Função para preencher os selects
function preencherSelect(endpoint, select) {
  fetch(`http://localhost:8080/${endpoint}`)
    .then(response => response.json())
    .then(data => {
      data.forEach(item => {
        const option = document.createElement("option");
        option.value = item.id;
        option.textContent = item.nome_usuario || item.forma_pagamento || item.nome_cliente || `ID ${item.id}`;
        select.appendChild(option);
      });
    })
    .catch(error => console.error(`Erro ao carregar ${endpoint}:`, error));
}

preencherSelect("usuario", inputUsuario);
preencherSelect("cliente", inputCliente);
preencherSelect("pagamento", inputPagamento);

// 3) Atualização dos dados da visão geral
function atualizarResumo() {
  const valorTotal = parseFloat(inputValor.value) || 0;
  const desconto = parseFloat(inputDesconto.value) || 0;

  const totalItens = valorTotal > 0 ? 1 : 0;
  const valorDesconto = valorTotal * (desconto / 100);
  const valorFinal = valorTotal - valorDesconto;

  totalItensSpan.textContent = totalItens;
  valorTotalSpan.textContent = `R$ ${valorTotal.toFixed(2)}`;
  totalDescontosSpan.textContent = `R$ ${valorDesconto.toFixed(2)}`;
  valorFinalSpan.textContent = `R$ ${valorFinal.toFixed(2)}`;
}

// 4) Eventos para atualizar dinamicamente
inputValor.addEventListener("input", atualizarResumo);
inputDesconto.addEventListener("input", atualizarResumo);

// 5) Aplicar desconto automaticamente ao escolher pagamento
inputPagamento.addEventListener("change", () => {
  const id = inputPagamento.value;
  if (!id) return;

  fetch(`http://localhost:8080/pagamento/${id}`)
    .then(res => res.json())
    .then(data => {
      descontoPagamento = data.desconto || 0;
      inputDesconto.value = descontoPagamento;
      atualizarResumo();
    })
    .catch(err => console.error("Erro ao buscar forma de pagamento:", err));
});

// 6) Cadastro da encomenda
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const valorTotal = parseFloat(inputValor.value) || 0;
  const desconto = parseFloat(inputDesconto.value) || 0;
  const valorFinal = valorTotal - (valorTotal * (desconto / 100));

  const encomenda = {
    descricao: inputDescricao.value,
    valorTotal: valorTotal,
    valorFinal: valorFinal,
    data: inputData.value,
    dataEntrega: inputDataEntrega.value,
    idCliente: parseInt(inputCliente.value),
    idUsuario: parseInt(inputUsuario.value),
    idPagamento: parseInt(inputPagamento.value)
  };

  fetch("http://localhost:8080/encomenda", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(encomenda)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Erro ao cadastrar encomenda.");
      }
      window.location.href = "gestao_encomendas.html";
    })
    .catch(error => {
      console.error("Erro ao enviar encomenda:", error);
      alert("Erro ao cadastrar encomenda. Verifique os dados.");
    });
});
