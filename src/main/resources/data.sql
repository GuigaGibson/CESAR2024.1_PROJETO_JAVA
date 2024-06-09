INSERT INTO ALUNO (ID, VERSION, MATRICULA, NOME, EMAIL, SERIE, TURMA, SENHA) VALUES
(1, 1, '20232GTIPL0312', 'João Silva', 'joao.silva@example.com', '1° ANO', 'B', 'senha123'),
(2, 1, '20232GTIPL0419', 'Maria Eduarda', 'dudaazv@example.com', '2° ANO', 'A', '123'),
(3, 1, '20232GTIPL0718', 'Rogerio Fernandes', 'rogerio678@example.com', '3° ANO', 'C', 'rgr789'),
(4, 1, '2023GTIPL0417', 'Mercio Luiz', 'mluiz89@example.com', '2° ANO', 'A', 'mercio209'),
(5, 1, '20232GTIPL457', 'Julia Ribeiro', 'juribeiro16@example.com', '1° ANO', 'B', 'ju123'),
(6, 1, '20232GTIPL457', 'Julia Ribeiro', 'juribeiro16@example.com', '1° ANO', 'B', 'ju123'),
(7, 1, '2024GTIZ1234', 'Gustavo Santos', 'gustavosantos@example.com', '2° ANO', 'A', 'gs456'),
(8, 1, '20242GTIX4321', 'Amanda Lima', 'amandalima@example.com', '3° ANO', 'B', 'al789'),
(9, 1, '20244GTI1234', 'Rafaela Souza', 'rafaelasouza@example.com', '1° ANO', 'C', 'rs321'),
(10, 1, '20246GTIV5432', 'Matheus Oliveira', 'matheusoliveira@example.com', '2° ANO', 'A', 'mo654'),
(11, 1, '20248GTI1234', 'Larissa Costa', 'larissacosta@example.com', '3° ANO', 'B', 'lc987'),
(12, 1, '20250GTI9876', 'Fernanda Silva', 'fernandasilva@example.com', '1° ANO', 'D', 'fs123'),
(13, 1, '20252GTI5678', 'Lucas Rodrigues', 'lucasrodrigues@example.com', '2° ANO', 'C', 'lr456'),
(14, 1, '20254GTI4321', 'Carolina Oliveira', 'carolinaoliveira@example.com', '3° ANO', 'A', 'co789'),
(15, 1, '20256GTI9876', 'Pedro Almeida', 'pedroalmeida@example.com', '1° ANO', 'B', 'pa321'),
(16, 1, '20258GTI5678', 'Mariana Pereira', 'marianapereira@example.com', '2° ANO', 'D', 'mp654'),
(17, 1, '20260GTI4321', 'Rodrigo Martins', 'rodrigomartins@example.com', '3° ANO', 'C', 'rm987');


INSERT INTO GESTOR (ID, VERSION, MATRICULA, NOME, EMAIL, SENHA) VALUES
(1,1,'20232ADMPL0876', 'Leandro Lima', 'leandro@gmail.com', '4444');

INSERT INTO eletivas (id, version, nome, descricao, professor, vagas_disponiveis, serie) VALUES
(16, 1, 'Fotografia Digital', 'Curso básico de fotografia digital, incluindo composição, iluminação e edição.', 'Maria Silva', 30, '1° ANO'),
(1, 1, 'Jogos Digitais', 'Curso introdutório ao design e desenvolvimento de jogos digitais.', 'Angela Tenório', 25, '2° ANO'),
(2, 1, 'IA em Jogos', 'Exploração de IA no desenvolvimento de jogos, incluindo aprendizado de máquina e algoritmos genéticos.', 'Ricardo Almeida', 20, '3° ANO'),
(3, 1, 'RV e RA', 'Conceitos de realidade virtual e aumentada no desenvolvimento de jogos.', 'Marina Oliveira', 15, '1° ANO'),
(4, 1, 'Desenvolvimento Web', 'Princípios básicos de desenvolvimento web: HTML, CSS, JavaScript e frameworks.', 'Pedro Santos', 10, '2° ANO'),
(5, 1, 'Desenvolvimento Mobile', 'Introdução ao desenvolvimento de aplicativos móveis para Android e iOS.', 'Luciana Lima', 20, '3° ANO'),
(6, 1, 'Data Science', 'Introdução à ciência de dados: coleta, análise e interpretação de dados.', 'Gustavo Silva', 30, '1° ANO'),
(7, 2, 'Marketing Digital', 'Fundamentos do marketing digital: mídias sociais, SEO, PPC e análise de dados.', 'Carolina Souza', 25, '2° ANO'),
(8, 2, 'Gestão de Projetos', 'Princípios de gestão de projetos: planejamento, execução, controle e metodologias ágeis.', 'Gabriel Oliveira', 20, '3° ANO'),
(9, 2, 'Empreendedorismo', 'Introdução ao empreendedorismo: identificação de oportunidades e desenvolvimento de planos de negócios.', 'Amanda Costa', 15, '1° ANO'),
(10, 3, 'Gestão de RH', 'Conceitos básicos de gestão de recursos humanos: recrutamento, seleção e treinamento.', 'Rafaela Santos', 10, '2° ANO'),
(11, 3, 'Liderança e Decisão', 'Princípios de liderança e tomada de decisões: habilidades de liderança, comunicação e resolução de problemas.', 'Lucas Oliveira', 30, '3° ANO'),
(12, 3, 'Negociação e Conflito', 'Estratégias de negociação e resolução de conflitos.', 'Ana Maria Rodrigues', 25, '1° ANO'),
(13, 4, 'Arquitetura Sustentável', 'Princípios da arquitetura sustentável e design verde.', 'José da Silva', 20, '2° ANO');
