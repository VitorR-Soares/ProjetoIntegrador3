// ====== CAPTURA DOS ELEMENTOS DO DOM ======
const inputData = document.getElementById("input_data");
const tabelaVendas = document.querySelector("#tabela-vendas tbody");
const btnExibeTodos = document.getElementById("btn_exibe_todos");
const btnFiltrar = document.querySelector(".top-bar input[type='submit']");

// ====== FUNÇÃO PARA ADICIONAR LINHAS NA TABELA ======
function adicionarLinhaNaTabela(venda) {
  const linha = document.createElement("tr");

  linha.innerHTML = `
    <td>${venda.id}</td>
    <td>R$ ${venda.valor_total.toFixed(2)}</td>
    <td>R$ ${venda.valor_final.toFixed(2)}</td>
    <td>${venda.data}</td>
    <td>${venda.cliente.nome_cliente}</td>
    <td>${venda.usuario.nome_usuario}</td>
    <td>${venda.pagamento.forma_pagamento}</td>
  `;

//  // Ao clicar na linha, buscar os produtos da venda e exibir em alert
//  linha.addEventListener("click", () => {
//    if (!venda.produtos || venda.produtos.length === 0) {
//      alert("Venda sem produtos registrados.");
//      return;
//    }
//
//    let texto = `Produtos da venda #${venda.id}:\n\n`;
//    venda.produtos.forEach(pv => {
//      fetch(`http://localhost:8080/produto/${pv.idProduto}`)
//        .then(res => res.json())
//        .then(produto => {
//          texto += `- ${produto.nome} (Qtd: ${pv.quantidade})\n`;
//          alert(texto); // exibe depois do último fetch
//        })
//        .catch(err => alert("Erro ao buscar produto: " + err));
//    });
//  });

  tabelaVendas.appendChild(linha);
}

// ====== FUNÇÃO PARA LISTAR TODAS AS VENDAS ======
function listarTodasVendas() {
  fetch("http://localhost:8080/venda")
    .then(res => res.json())
    .then(lista => {
      limparTabela();
      lista.forEach(venda => adicionarLinhaNaTabela(venda));
    })
    .catch(err => alert("Erro ao carregar vendas: " + err));
}

// ====== FUNÇÃO PARA FILTRAR POR DATA ======
function filtrarPorData() {
  const data = inputData.value;

  if (!data) {
    alert("Selecione uma data.");
    return;
  }

  fetch(`http://localhost:8080/venda/data/${data}`)
    .then(res => {
      if (!res.ok) throw new Error("Nenhuma venda encontrada na data.");
      return res.json();
    })
    .then(lista => {
      limparTabela();
      lista.forEach(venda => adicionarLinhaNaTabela(venda));
    })
    .catch(err => alert(err.message));
}

// ====== FUNÇÃO AUXILIAR ======
function limparTabela() {
  tabelaVendas.innerHTML = "";
}

// ====== EVENTOS ======
btnFiltrar.addEventListener("click", event => {
  event.preventDefault();
  filtrarPorData();
});

btnExibeTodos.addEventListener("click", listarTodasVendas);

// ====== INICIALIZAÇÃO ======
listarTodasVendas();



