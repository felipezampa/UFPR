package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import model.pedido.Cliente;
import model.tabela.ClienteTable;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class ClienteCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtTelefone;
	private JTextField txtFiltro;

	ClienteTable tabelaCliente = new ClienteTable();
	private List<Cliente> listaDeClientes;
	private JTable tbCliente;
	int linhaSelecionada = -1;

	/**
	 * Create the frame.
	 */
	public ClienteCadastro(List<Cliente> listaClientes) {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 486);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Cadastro Clientes");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(11, 12, 414, 19);
		contentPane.add(lblTitulo);
		
		JPanel panelDados = new JPanel();
		panelDados.setLayout(null);
		panelDados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDados.setBounds(41, 55, 354, 96);
		contentPane.add(panelDados);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(46, 8, 95, 20);
		panelDados.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(161, 8, 131, 20);
		panelDados.add(txtNome);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSobrenome.setBounds(46, 38, 95, 20);
		panelDados.add(lblSobrenome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(161, 38, 131, 20);
		panelDados.add(txtSobrenome);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setBounds(46, 69, 95, 20);
		panelDados.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(161, 69, 131, 20);
		panelDados.add(txtTelefone);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirCliente();
			}
		});
		btnCadastrar.setBounds(41, 162, 105, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});
		btnAtualizar.setEnabled(false);
		btnAtualizar.setBounds(156, 162, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(255, 162, 89, 23);
		contentPane.add(btnExcluir);
		
		JPanel panelTabela = new JPanel();
		panelTabela.setLayout(null);
		panelTabela.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTabela.setBounds(41, 221, 354, 207);
		contentPane.add(panelTabela);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 334, 157);
		panelTabela.add(scrollPane);
		
		tbCliente = new JTable();
		tbCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAtualizar.setEnabled(true);
				btnExcluir.setEnabled(true);
				preencherEdicaoCliente(e);
			}
		});
		scrollPane.setViewportView(tbCliente);
		tbCliente.setModel(tabelaCliente);
		
		JLabel lblFiltro = new JLabel("Filtrar:");
		lblFiltro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFiltro.setBounds(203, 14, 46, 14);
		panelTabela.add(lblFiltro);
		
		txtFiltro = new JTextField();
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarLista();
			}
		});
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(258, 11, 86, 20);
		panelTabela.add(txtFiltro);
		
		this.listaDeClientes = listaClientes; 
		tabelaCliente.atualizarTabela(listaClientes);
	}
	
    private String firstCharUpperCase(String str) {
        String ret = str.trim().toLowerCase();
        if (ret.isEmpty()) return "String vazia!"; // poderia ser Exception VAI SER!

        return ret.substring(0, 1).toUpperCase() + ret.substring(1);
    }

    private String validadorSobrenome(String str) {
        String[] sobrenomes = str.split(" ");
        String retorno = "";

        for (String sobrenome : sobrenomes) {
            retorno += firstCharUpperCase(sobrenome) + " ";
        }

        return retorno.trim();
    }

    private String validadorTelefone(String tel) {
        String telLimpo = tel.trim().replaceAll("[^0-9]", "");
        if (telLimpo.length() != 11) return "Telefone inválido!"; // poderia ser Exception

        return "(" +
                telLimpo.substring(0, 2) +
                ") " +
                telLimpo.substring(2, 6) +
                "-" +
                telLimpo.substring(6);
    }

    private void incluirCliente() {
        String nome = firstCharUpperCase(txtNome.getText());
        String sobrenome = validadorSobrenome(txtSobrenome.getText());
        String telefone = validadorTelefone(txtTelefone.getText());

        boolean clienteExistente = listaDeClientes.stream()
                .map(Cliente::getTelefone)
                .anyMatch(t -> t.equals(telefone));

        if (!clienteExistente) {
            Cliente cliente = new Cliente(nome, sobrenome, telefone);
            listaDeClientes.add(cliente);
            tabelaCliente.adicionarCliente(cliente);
        }
    }

    private void preencherEdicaoCliente(MouseEvent evt) {
        linhaSelecionada = tbCliente.rowAtPoint(evt.getPoint());
        Cliente cliente = tabelaCliente.getCliente(linhaSelecionada);

        txtNome.setText(cliente.getNome());
        txtSobrenome.setText(cliente.getSobrenome());
        txtTelefone.setText(cliente.getTelefone());
    }

    private void alterarCliente() {
        if (linhaSelecionada != -1) {
            Cliente cliente = tabelaCliente.getCliente(linhaSelecionada);

            cliente.setNome(firstCharUpperCase(txtNome.getText()));
            cliente.setSobrenome(validadorSobrenome(txtSobrenome.getText()));
            cliente.setTelefone(validadorTelefone(txtTelefone.getText()));

            tabelaCliente.fireTableRowsUpdated(linhaSelecionada, linhaSelecionada);
        }
    }

    private void excluirCliente() {
        int[] linhasSelecionadas = tbCliente.getSelectedRows();
        List<Cliente> listaExcluir = new ArrayList();

        for (int linhaSelecionada : linhasSelecionadas) {
            Cliente contato = tabelaCliente.getCliente(linhaSelecionada);
            listaExcluir.add(contato);
        }

        for (Cliente contato : listaExcluir) {
            listaDeClientes.remove(contato);
            tabelaCliente.removeCliente(contato);
        }
    }

    private void filtrarLista() {
        String filtroPalavra = txtFiltro.getText();
        if (!filtroPalavra.isBlank()) {
            var listaFiltrada = listaDeClientes.stream()
                    .filter(c -> c.getSobrenome().contains(filtroPalavra) ||
                            c.getTelefone().contains(filtroPalavra))
                    .collect(Collectors.toList());
            tabelaCliente.atualizarTabela(listaFiltrada);
        } else {
            tabelaCliente.atualizarTabela(listaDeClientes);
        }
    }
    
    

}
