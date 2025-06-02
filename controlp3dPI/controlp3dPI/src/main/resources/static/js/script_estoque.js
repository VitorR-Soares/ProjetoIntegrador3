const usuarioLogado = sessionStorage.getItem("usuarioLogado");

if (!usuarioLogado) {
  window.location.href = "login.html"; // redireciona se não estiver logado
}

const tbody = document.querySelector(".product-table tbody");
  const btn_exibir_todos = document.getElementById("btn_exibir_todos");
  const select_categoria = document.querySelector('#form_pesquisa_categoria select');
  const form_pesquisa_nome = document.getElementById("form_pesquisa_nome");
  const input = form_pesquisa_nome.querySelector(".input-text");
  const form_pesquisa_categoria = document.getElementById("form_pesquisa_categoria");
  const select_produtos = document.getElementById("select_produtos");
  const add_product_form = document.getElementById("add-product-form");
  const input_qtd = document.getElementById("input_qtd");
  // ROTINA PARA PREENCIMENTO DA TABELA (GENÉRICO)
  const preencherTabela = (produtos) => {
      
        tbody.innerHTML = "";
      
          produtos.forEach(produto => {
          const tr = document.createElement("tr");
          tr.classList.add("product-row");

          // Nome
          const tdNome = document.createElement("td");
          tdNome.textContent = produto.nome_produto;
          tdNome.classList.add("product-name");

          // Valor Unitário
          const tdValor = document.createElement("td");
          tdValor.textContent = `R$ ${produto.valor_unit.toFixed(2)}`;
          tdValor.classList.add("product-price");

          // Categoria
          const tdColecao = document.createElement("td");
          tdColecao.textContent = produto.categoria?.nome_categoria || "Sem coleção";
          tdColecao.classList.add("product-collection");
          
          // Quantidade
          const tdQtd = document.createElement("td");
          tdQtd.textContent = produto.qtd;
          tdQtd.classList.add("product-qtd");

          // Alterações
          const tdAlteracoes = document.createElement("td");
          tdAlteracoes.classList.add("product-actions");

          const btnAtualizar = document.createElement("button");
          btnAtualizar.textContent = "Atualizar";
          btnAtualizar.classList.add("btn-atualizar");
          btnAtualizar.onclick = () => {
            // Aqui você pode abrir um modal ou redirecionar para a tela de edição
            console.log("Atualizar produto ID:", produto.id);
          };

          const btnExcluir = document.createElement("button");
          btnExcluir.textContent = "Excluir";
          btnExcluir.classList.add("btn-excluir");
          btnExcluir.onclick = () => {
            if (confirm("Deseja realmente excluir este produto?")) {
              fetch(`http://localhost:8080/produto/${produto.id}`, {
                method: "DELETE"
              })
              .then(response => {
                if (response.ok) {
                  tr.remove(); // Remove a linha da tabela
                } else {
                  alert("Erro ao excluir produto.");
                }
              });
            }
          };

          tdAlteracoes.appendChild(btnAtualizar);
          tdAlteracoes.appendChild(btnExcluir);

          // Monta a linha
          tr.appendChild(tdNome);
          tr.appendChild(tdValor);
          tr.appendChild(tdColecao);
          tr.appendChild(tdQtd);
          tr.appendChild(tdAlteracoes);

          // Adiciona a linha na tabela
          tbody.appendChild(tr);
        });
      
  }
  // PREENCHIMENTO DA TABELA DE ESTOQUE EXIBINDO A LISTA COMPLETA DE PRODUTOS
  const preencheTabela = () => {
      fetch("http://localhost:8080/produto")
      .then(response => {
        if (!response.ok) {
          throw new Error("Erro ao buscar produtos");
        }
        return response.json();
      })
      .then(produtos => {
          
        console.log(produtos);
        preencherTabela(produtos);

      })
      .catch(error => {
        console.error("Erro ao carregar produtos:", error);
      });
}


// ROTINA PESQUISA POR NOME
form_pesquisa_nome.addEventListener("submit", (event) => {
    event.preventDefault();

    const valorPesquisa = input.value.trim();

    if (valorPesquisa === "") {
      alert("Digite um nome para pesquisar.");
      return;
    }

    fetch(`http://localhost:8080/produto/pesquisaNome/${encodeURIComponent(valorPesquisa)}`)
    .then(response => {
        if (!response.ok) {
          throw new Error("Erro ao buscar produtos");
        }
          return response.json();
        })
    .then(produtos => {
          preencherTabela(produtos);
        })
    .catch(error => {
        console.error("Erro na busca:", error);
        alert("Ocorreu um erro na busca. Verifique se o servidor está rodando.");
    });
});
// ROTINA PESQUISA POR CATEGORIA
form_pesquisa_categoria.addEventListener("submit", (event) => {
   event.preventDefault(); // evita reload da página
   const idCategoria = select_categoria.value;
   console.log(idCategoria);

    if (!idCategoria) {
      alert("Por favor, selecione uma categoria.");
      return;
    }
    fetch(`http://localhost:8080/produto/pesquisaCategoria/${idCategoria}`)
            .then(response => {
                if(!response.ok){
                    throw new Error("Erro ao buscar produtos");
                }
                return response.json();
            })
            .then(produtos => {
                console.log(produtos);
                preencherTabela(produtos);
            })
            .catch(error => {
                console.error("Erro na busca:", error);
                alert("Ocorreu um erro na busca. Verifique se o servidor está rodando.");
            });
});

// ROTINA PREENCHER SELECT DE PRODUTOS

const chamaProduto = () => {    
    console.log("Antes do fetch");
    fetch("http://localhost:8080/produto")
      .then(response => {
        if (!response.ok) {
          throw new Error("Erro ao buscar produtos");
        }
        return response.json();
      })
      .then(produtos => {          
        produtos.forEach(produto => {
            console.log(produto.id);
            console.log(produto.nome_produto);
            
            const option = document.createElement("option");
            option.setAttribute("value", produto.id);
            option.textContent = produto.nome_produto;
            
            select_produtos.appendChild(option);
            
        });       

      })
      .catch(error => {
        console.error("Erro ao carregar produtos:", error);
      });
    
    
};

// ROTINA ATUALIZAR QUANTIDADE EM ESTOQUE
    
add_product_form.addEventListener("submit", (evt)=> {
    evt.preventDefault();
    
    const idProduto = select_produtos.value;
    const qtdAdd = input_qtd.value;
    
    console.log(idProduto);
    console.log(qtdAdd);
    
    if(!idProduto || idProduto === "Selecione um produto..."){
        alert("Por favor, selecione um produto");
        return;
    }
    if(!qtdAdd || qtdAdd <=0 ){
        alert("Por favor, adicione uma quantidade válida");
        return;
    }
    const cabecalho = {
        method: "PUT",
        headers: {'Content-Type': 'application/json' }
    };
    fetch(`http://localhost:8080/produto/${idProduto}/${qtdAdd}`, cabecalho)
        .then(res => {
            if(!res.ok){
                throw new Error("Erro ao atualizar estoque do produto");
            }
            return res.json();   
        })
        .then(res => {
            alert(`Produto ${res.nome_produto} atualziado com sucesso`);
            preencheTabela();
        })
        .catch(error => console.log("Erro" + error));
    
    
});

// BOTÃO EXIBIR TODOS
btn_exibir_todos.addEventListener("click", ()=> {
    preencheTabela();
});
// PREENCHER SELECT DE CATEGORIAS
const preencheCategorias = () => {    
    fetch('http://localhost:8080/categoria')
            .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao buscar produtos");
            }
            return response.json();
            })
            .then(categorias => {
                categorias.forEach(categoria => {
                    console.log(categoria.nome_categoria);
                    const option = document.createElement('option');
                    option.value = categoria.id; // ou outro nome de campo de ID da categoria
                    option.textContent = categoria.nome_categoria; // ou outro campo como 'descricao'
                    select_categoria.appendChild(option);
                });
            })
            .catch(error => {
                console.error("Erro na busca:", error);
                alert("Ocorreu um erro na busca. Verifique se o servidor está rodando.");
            });   
};

chamaProduto();
preencheTabela();
preencheCategorias();



