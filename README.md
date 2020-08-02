#Teste Processo seletivo Datainfo.
Autor: Claudinei de Souza

>Sistema de consulta de processo judicial

>Projeto base para um projeto novo, tela mestre-detalhe utilizando componentes primefaces e obedecendo a responsividade para dispositivos com telas menores.

## Especificação do Teste
>Desenvolva uma API RESTful para possibilitar o cadastro de processos e a sua distribuição
para análise de um juiz cadastrado no sistema com menor número de processos.

## Requisito do sistema
1. [ ] Ler o arquivo ​TABELA_DE_CLASSES_ATIVAS.csv ​e inserir os dados em uma base de
dados ao iniciar a aplicação;
1. [X] Cadastrar, listar e pesquisa de processo;
<ol>
	<li> [X] O número do processo (​campo nr_processo tabela tb_processo​) deve ser
		calculado conforme a regra a seguir:
	</li>
	<ul>
		<li>[X] NNNNNNN-MM.AAAA.JTR - > 0000100-15.2008.811</li>
		<li>[X] NNNNNNN - Id do processo com preenchimento de zeros a esquerda</li>
		<li>[X] MM - Mês de criação do processo</li>
		<li>[X] AAAA - Ano de criação do processo</li>
		<li>[X] JTR - Sempre será 811.</li>
	</ul>
	<li>[X] Um processo somente poderá ser cadastrado junto com as partes.</li>
	<li>[X] Um processo não poderá ser cadastrado com tipos de partes iguais.</li>
	<li>[X] Obrigatório a inclusão de duas partes no processo.</li>
	<li>[X] Obrigatório a inclusão de uma classe junto ao processo.</li>
</ol>
1. [X] API deve permitir o cadastro, listagem e pesquisa das partes de um processo.
<ol>
	<li> As partes de um processo deverão ser incluídas somente na criação do
		processo. Ex: autor e réu
	</li>
</ol>
1. [X] Cadastrar, listar e pesquisa de juiz
1. [ ] API deve ter um serviço que monitore a cada 5 minutos os processos cadastrados,
	fazendo o vínculo do mesmo com o juiz que tem menos processos ( pode escolher o
	tempo, mas o mesmo não pode ser superior a 10 ) .
<ol>
	<li>[X]  Na ​tb_processo​ existe o campo ​data_distribuicao​ que indica se o processo foi ou não
encaminhado para algum juiz.
	</li>
</ol>
1.[X] Validar o endereço fornecido pela parte no momento do cadastro em algum serviço de
busca de endereço.Ex : <a href ="https://viacep.com.br/">VIACEP</a>

## Tecnologias/Outros:
1. Spring Boot 2.3.2
1. Java 8.
1. hibernate-validator.
1. MAVEN (Dependency Manager).
1. postgresql 42.2.
1. lombok 1.18.
