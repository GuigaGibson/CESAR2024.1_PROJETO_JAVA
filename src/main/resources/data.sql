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



INSERT INTO eletivas (id, version, nome, descricao, professor)
VALUES
(16,1,'Fotografia Digital', 'Nesta eletiva, os alunos aprenderão os princípios básicos da fotografia digital, incluindo composição, iluminação e edição de imagens. Além disso, explorarão o uso de câmeras digitais e softwares de edição para aperfeiçoar suas próprias fotografias.', 'Maria Silva'),
(1, 1, 'Jogos Digitais', 'Este curso oferece uma introdução abrangente ao design e desenvolvimento de jogos digitais. Os alunos aprenderão os princípios básicos do design de jogos, desenvolvimento de narrativas, arte e design de personagens, programação de jogos e muito mais.', 'Angela Tenório'),
(2, 1, 'Inteligência Artificial em Jogos', 'Neste curso, os alunos explorarão o papel da inteligência artificial (IA) no desenvolvimento de jogos. Eles aprenderão sobre algoritmos de IA comuns usados em jogos, como aprendizado de máquina, algoritmos genéticos e técnicas de busca.', 'Ricardo Almeida'),
(3, 1, 'Realidade Virtual e Aumentada', 'Este curso aborda os conceitos fundamentais de realidade virtual (RV) e realidade aumentada (RA) e como aplicá-los no desenvolvimento de jogos. Os alunos aprenderão a criar experiências imersivas usando tecnologias de RV e RA.', 'Marina Oliveira'),
(4, 1, 'Desenvolvimento Web', 'Princípios básicos do desenvolvimento web: HTML, CSS, JavaScript e frameworks como React e Angular.', 'Pedro Santos'),
(5, 1, 'Desenvolvimento Mobile', 'Introdução ao desenvolvimento de aplicativos móveis para Android e iOS usando Android Studio e Xcode.', 'Luciana Lima'),
(6, 1, 'Data Science', 'Introdução à ciência de dados: coleta, análise e interpretação de dados usando Python e bibliotecas como Pandas e NumPy.', 'Gustavo Silva'),
(7, 2, 'Marketing Digital', 'Fundamentos do marketing digital: mídias sociais, SEO, PPC e análise de dados para campanhas eficazes online.', 'Carolina Souza'),
(8, 2, 'Gestão de Projetos', 'Princípios da gestão de projetos: planejamento, execução, controle e metodologias ágeis como Scrum e Kanban.', 'Gabriel Oliveira'),
(9, 2, 'Empreendedorismo', 'Introdução ao empreendedorismo: identificação de oportunidades, desenvolvimento de planos de negócios e lançamento de startups.', 'Amanda Costa'),
(10, 3, 'Gestão de RH', 'Conceitos básicos de gestão de recursos humanos: recrutamento, seleção, treinamento e desenvolvimento de funcionários.', 'Rafaela Santos'),
(11, 3, 'Liderança e Decisão', 'Princípios da liderança e tomada de decisões: habilidades de liderança, comunicação e resolução de problemas.', 'Lucas Oliveira'),
(12, 3, 'Negociação e Conflito', 'Estratégias de negociação e resolução de conflitos: habilidades para negociar acordos e resolver disputas de forma construtiva.', 'Ana Maria Rodrigues'),
(13, 4, 'Arquitetura Sustentável', 'Princípios da arquitetura sustentável e design verde: projetando edifícios e espaços urbanos ambientalmente responsáveis e energeticamente eficientes.', 'José da Silva');