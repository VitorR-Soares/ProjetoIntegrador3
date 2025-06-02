const usuarioLogado = sessionStorage.getItem("usuarioLogado");

if (!usuarioLogado) {
  window.location.href = "login.html"; // redireciona se não estiver logado
}

// Captura de elementos do DOM
const inputData = document.getElementById("input_data");
const btnFiltrar = document.querySelector(".top-bar .btn");
const btnExibeTodas = document.querySelector(".bottom-buttons .btn");
const tabela = document.querySelector("#tabela_encomendas tbody");

// Função para criar uma linha da tabela
function criarLinha(encomenda) {
  const tr = document.createElement("tr");

  // Estilização da linha por status
  if (encomenda.status === "Pendente") {
    tr.style.setProperty("background-color", "#fff8b3", "important"); // Amarelo claro
  } else if (encomenda.status === "Entregue") {
    tr.style.setProperty("background-color", "#c8f7c5", "important"); // Verde claro
  }

  tr.innerHTML = `
    <td>${encomenda.id}</td>
    <td>R$ ${parseFloat(encomenda.valor_total).toFixed(2)}</td>
    <td>${encomenda.data}</td>
    <td>${encomenda.data_entrega}</td>
    <td>${encomenda.cliente?.nome_cliente || "N/A"}</td>
    <td>${encomenda.usuario?.nome_usuario || "N/A"}</td>
    <td>${encomenda.pagamento?.forma_pagamento || "N/A"}</td>
    <td>
      <input type="checkbox" ${encomenda.status === "Entregue" ? "checked disabled" : ""} data-id="${encomenda.id}">
    </td>
  `;

  // Evento do checkbox para mudar o status
  const checkbox = tr.querySelector("input[type='checkbox']");
  if (checkbox && !checkbox.disabled) {
    checkbox.addEventListener("change", () => {
      if (checkbox.checked) {
        fetch(`http://localhost:8080/encomenda/${encomenda.id}/entregar`, {
          method: "PUT"
        })
          .then(response => {
            if (!response.ok) throw new Error("Erro ao atualizar status.");
            checkbox.disabled = true;
            tr.style.setProperty("background-color", "#c8f7c5", "important");
          })
          .catch(err => {
            checkbox.checked = false;
            alert("Erro ao atualizar status: " + err.message);
          });
      }
    });
  }

  tabela.appendChild(tr);
}

// Função para carregar encomendas
function carregarEncomendas(endpoint) {
  tabela.innerHTML = "";

  fetch(endpoint)
    .then(res => res.json())
    .then(data => {
      if (!Array.isArray(data)) {
        alert("Erro: resposta inesperada da API.");
        return;
      }

      data.forEach(encomenda => criarLinha(encomenda));
    })
    .catch(err => {
      console.error("Erro ao buscar encomendas:", err);
      alert("Erro ao carregar encomendas.");
    });
}

// 1) Listar todas as encomendas
carregarEncomendas("http://localhost:8080/encomenda");

// 2) Filtrar por data de entrega
btnFiltrar.addEventListener("click", () => {
  const data = inputData.value;
  if (!data) {
    alert("Informe uma data para filtrar.");
    return;
  }

  carregarEncomendas(`http://localhost:8080/encomenda/data/${data}`);
});

// 3) Exibir todas
btnExibeTodas.addEventListener("click", () => {
  carregarEncomendas("http://localhost:8080/encomenda");
});

