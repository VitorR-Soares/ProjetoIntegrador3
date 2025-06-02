const usuarioLogado = sessionStorage.getItem("usuarioLogado");

if (!usuarioLogado) {
  window.location.href = "login.html"; // redireciona se não estiver logado
}

// ====== CAPTURA DOS ELEMENTOS DO DOM ======
const inputProduto = document.getElementById("input_produto");
const inputQtd = document.getElementById("input_qtd");
const inputCliente = document.getElementById("input_cliente");
const inputUsuario = document.getElementById("input_usuario");
const inputPagamento = document.getElementById("input_pagamento");
const inputData = document.getElementById("input_data");
const inputDesconto = document.getElementById("input_desconto");

const btnAddCarrinho = document.getElementById("btn_add_carrinho");
const btnVisualizarCarrinho = document.getElementById("btn_visualiza_carrinho");
const btnFinalizarVenda = document.getElementById("btn_finalizar_venda");
const btnAplicarDesconto = document.querySelector(".aplicar-btn");

const spanTotalItens = document.getElementById("total-itens");
const spanValorTotal = document.getElementById("valor-total");
const spanTotalDescontos = document.getElementById("total-descontos");
const spanValorFinal = document.getElementById("valor-final");

// ====== VARIÁVEIS DE CONTROLE ======
let carrinho = [];
let valorTotal = 0;
let valorFinal = 0;

// ====== FUNÇÕES DE INICIALIZAÇÃO ======
function carregarSelect(url, selectElement) {
  fetch(url)
    .then(res => res.json())
    .then(lista => {
      lista.forEach(item => {
        const option = document.createElement("option");
        option.value = item.id;
        option.textContent = item.nome_cliente || item.nome_usuario || item.forma_pagamento || item.nome_produto;
        selectElement.appendChild(option);
      });
    })
    .catch(err => alert("Erro ao carregar dados: " + err));
}

function inicializar() {
  carregarSelect("http://localhost:8080/produto", inputProduto);
  carregarSelect("http://localhost:8080/cliente", inputCliente);
  carregarSelect("http://localhost:8080/usuario", inputUsuario);
  carregarSelect("http://localhost:8080/pagamento", inputPagamento);
}

inicializar();

// ====== FUNÇÃO: ADICIONAR AO CARRINHO ======
btnAddCarrinho.addEventListener("click", () => {
  const idProduto = parseInt(inputProduto.value);
  const qtd = parseInt(inputQtd.value);

  if (isNaN(idProduto) || isNaN(qtd) || qtd <= 0) {
    alert("Selecione um produto válido e uma quantidade válida.");
    return;
  }

  const produtoSelecionado = Array.from(inputProduto.options).find(opt => parseInt(opt.value) === idProduto);
  const nomeProduto = produtoSelecionado.textContent;

  fetch(`http://localhost:8080/produto/${idProduto}`)
    .then(res => res.json())
    .then(produto => {
      const subtotal = produto.valor_unit * qtd;
      carrinho.push({ idProduto, nome: nomeProduto, quantidade: qtd, valor_unit: produto.valor_unit, subtotal });
      atualizarResumo();
      alert("Produto adicionado ao carrinho!");
      inputQtd.value = "";
    })
    .catch(err => alert("Erro ao buscar produto: " + err));
});

// ====== FUNÇÃO: VISUALIZAR CARRINHO ======
btnVisualizarCarrinho.addEventListener("click", () => {
  let texto = "Itens no Carrinho:\n\n";
  carrinho.forEach((item, index) => {
    texto += `${index + 1}. ${item.nome} - Quantidade: ${item.quantidade} - Subtotal: R$ ${item.subtotal.toFixed(2)}\n`;
  });
  alert(texto);
});

// ====== FUNÇÃO: ATUALIZAR RESUMO DINÂMICO ======
function atualizarResumo() {
  const totalItens = carrinho.reduce((acc, item) => acc + item.quantidade, 0);
  valorTotal = carrinho.reduce((acc, item) => acc + item.subtotal, 0);
  const descontoPercent = parseFloat(inputDesconto.value) || 0;
  const descontoValor = valorTotal * (descontoPercent / 100);
  valorFinal = valorTotal - descontoValor;

  spanTotalItens.textContent = totalItens;
  spanValorTotal.textContent = `R$ ${valorTotal.toFixed(2)}`;
  spanTotalDescontos.textContent = `R$ ${descontoValor.toFixed(2)}`;
  spanValorFinal.textContent = `R$ ${valorFinal.toFixed(2)}`;
}

// ====== FUNÇÃO: APLICAR DESCONTO ======
btnAplicarDesconto.addEventListener("click", () => {
  atualizarResumo();
});

// ====== FUNÇÃO: FINALIZAR VENDA ======
btnFinalizarVenda.addEventListener("click", event => {
  event.preventDefault();

  if (carrinho.length === 0) {
    alert("Adicione pelo menos um produto ao carrinho!");
    return;
  }

  const vendaDTO = {
    data: inputData.value,
    idCliente: parseInt(inputCliente.value),
    idUsuario: parseInt(inputUsuario.value),
    idPagamento: parseInt(inputPagamento.value),
    valorTotal: valorTotal,
    valorFinal: valorFinal,
    produtos: carrinho.map(item => ({
      idProduto: item.idProduto,
      quantidade: item.quantidade
    }))
  };

  fetch("http://localhost:8080/venda", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(vendaDTO)
  })
    .then(res => {
      if (!res.ok) throw new Error("Erro ao cadastrar venda");
      return res.json();
    })
    .then(() => {
      alert("Venda cadastrada com sucesso!");
      window.location.href = "gestao_vendas.html";
    })
    .catch(err => alert("Erro ao finalizar venda: " + err.message));
});


