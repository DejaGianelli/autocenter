-- Vehicle Brands

INSERT INTO vehicle_brands (internal_id, name) VALUES (1, "Peugeot");

-- Vehicle Models

INSERT INTO vehicle_models (internal_id, brand_id, name) VALUES (1, 1, "208");

-- Suppliers

INSERT INTO suppliers (internal_id, public_id, cnpj, name) VALUES (1, "01JQAHVBW58F7N34FPEPJ96ZMS", "02744280000178", "Some Company");

-- Financial Accounts

INSERT INTO financial_accounts (category, name) VALUES
('1', 'Ativos'),
('1.1', 'Ativos Circulantes'),
('1.1.1', 'Caixa e Equivalentes de Caixa'),
('1.1.2', 'Contas a Receber'),
('1.1.3', 'Estoques'),
('1.1.4', 'Despesas Antecipadas'),
('1.1.5', 'Compras a Receber'),
('1.2', 'Ativos Não Circulantes'),
('1.2.1', 'Imobilizado'),
('1.2.1.1', 'Máquinas e Equipamentos'),
('1.2.1.2', 'Veículos'),
('1.2.2', 'Intangível'),
('1.2.2.1', 'Marcas e Patentes');

-- Inserir contas contábeis de "Passivo"
INSERT INTO financial_accounts (category, name) VALUES
('2', 'Passivos'),
('2.1', 'Passivos Circulantes'),
('2.1.1', 'Contas a Pagar'),
('2.1.2', 'Empréstimos e Financiamentos de Curto Prazo'),
('2.1.3', 'Obrigações Fiscais'),
('2.2', 'Passivos Não Circulantes'),
('2.2.1', 'Empréstimos e Financiamentos de Longo Prazo'),
('2.2.2', 'Provisões de Longo Prazo');

-- Inserir contas contábeis de "Patrimônio Líquido"
INSERT INTO financial_accounts (category, name) VALUES
('3', 'Patrimônio Líquido'),
('3.1', 'Capital Social'),
('3.2', 'Lucros Acumulados');

-- Inserir contas contábeis de "Receitas"
INSERT INTO financial_accounts (category, name) VALUES
('4', 'Receitas'),
('4.1', 'Receitas Operacionais'),
('4.1.1', 'Vendas de Produtos'),
('4.1.2', 'Receitas de Serviços'),
('4.2', 'Receitas Não Operacionais'),
('4.2.1', 'Receitas de Juros');

-- Inserir contas contábeis de "Despesas"
INSERT INTO financial_accounts (category, name) VALUES
('5', 'Despesas'),
('5.1', 'Despesas Operacionais'),
('5.1.1', 'Custo das Mercadorias Vendidas (CMV)'),
('5.1.2', 'Despesas com Vendas e Marketing'),
('5.1.3', 'Despesas Administrativas'),
('5.2', 'Despesas Não Operacionais'),
('5.2.1', 'Despesas Financeiras (Juros Pagos)');