# ed2;rcelo

Repositório do trabalho de Estrutura de Dados (DCC012) do Depto de Ciência da Computação da UFJF referente ao semestre 2020.1.

## Book Depository Dataset :closed_book:

O conjunto de dados (registros) em *.csv* está disponível em [Kaggle](https://www.kaggle.com/sp1thas/book-depository-dataset). É necessário fazer o *download* do arquivo para executar o programa e deixá-lo na pasta do projeto para poder rodá-lo. Você pode encontrá-lo em [Google Drive](https://drive.google.com/file/d/1Iy7PVAZdvuUFuK3FiepMJVy2TkX_qY2H/view?usp=sharing).

**Observação**: Para rodar o programa, além do arquivo *authors.csv* do *DataSet* estar no diretório, o arquivo de entrada especificado na descrição do trabalho também deve estar, *entrada.txt*. Para rodar via terminal no Linux basta baixar o Trabalho 01, 02 ou 03 com a extenção *.jar do repositório de cada parte e executar o comando:

```java -jar Trabalho01.jar```, ```java -jar Trabalho02.jar``` ou ```java -jar Trabalho03.jar```.

### Parte I

#### Análise dos Algoritmos de Ordenação :chart_with_upwards_trend:
- [x] Armazenar os registros em um vetor de tamanho N (*authors [list], bestsellers-rank [int], categories [list], edition [str], id [int], isbn10 [str], isbn13 [str], rating-avg [int], rating-count [int], title [str]*); 
- [x] Contabilizar o número de comparações de chaves;
- [x] Contabilizar o número de cópias de registros;
- [x] Contabilizar o tempo total gasto na ordenação (tempo de máquina, não de relógio).

##### Escolha dos algoritmos :bookmark_tabs:

* QuickSort
* HeapSort

**Observação:** O programa deve receber um arquivo de entrada (*entrada.txt*) com o formato: *N* é número de valores que se seguem, um por linha. Exemplo: *N = 3*, então cada linha terá um valor especificado de 1000 na primeira linha, 5000 na segunda e 10000 na terceira.

Para cada valor de *N* lido do arquivo *entrada.txt*:
- [x] Gera cada um dos conjuntos dos elementos, ordena, contabiliza estatísticas de desempenho;
- [x] Armazena estatísticas de desempenho em arquivo de saída (*saida.txt*).

### Parte II
#### Implementação dos Autores mais Frequentes :bar_chart:

Implementar um programa que leia N livros aleatórios e diferentes e conte quantas vezes um mesmo autor se repete dentro desses N livros por meio de uma tabela Hash e imprimir os autores mais frequentes.

- [x] Tabela Hash de Registros;
- [x] Tabela Hash de Autores.


### Parte III
#### Busca em Estruturas Balanceadas e Auto-Ajustáveis :bookmark:

Você deverá avaliar o desempenho de estruturas balanceadas ao inserir um livro usando como chave o id do livro. Você também deverá analisar o desempenho dessas estruturas ao realizar a busca pelo livro. As estruturas que devem ser avaliadas são:

- [x] Árvore Vermelho-Preto;
- [x] Árvore B (d=2);
- [x] Árvore B (d=20).

Para cada valor de N lido do arquivo de entrada:

* Gera cada um dos conjuntos de elementos, constrói a árvore, contabiliza estatísticas de desempenho para inserção na árvore analisada;
* Armazena estatísticas de desempenho em arquivo de saída (saidaInsercao.txt);
* Realiza a busca na árvore gerada pelas entradas da inserção, contabiliza estatísticas de desempenho para busca na árvore analisada;
* Armazena estatísticas de desempenho em arquivo de saída (saidaBusca.txt).

Ao final, basta processar os arquivos de saída referentes a cada uma das sementes, calculando as médias de cada estatística, para cada valor de N e para cada estrutura de
dados considerados.
