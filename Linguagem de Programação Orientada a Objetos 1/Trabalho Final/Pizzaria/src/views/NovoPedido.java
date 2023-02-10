package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Util;
import model.formatos.Pizza;
import model.pedido.Cliente;
import model.pedido.Pedido;
import model.pedido.Status;
import model.sabor.PrecoSabor;
import model.sabor.Sabor;
import model.tabela.PizzaTable;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class NovoPedido extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeCompleto;
	private JTable table;
    private JComboBox comboCliente;
    JButton btnNovo;
    JButton btnAtualizar;
    JButton btnExcluir;
    JLabel lblTotal;
    
	private JTextField txtFiltro;
	
	private List<Cliente> listaDeClientes;
	private List<String> listaTelefone;
	
	private Pedido pedido;
	private PizzaTable tabelaPizza = new PizzaTable();
	private Cliente clienteSelecionado;
	private Pizza pizzaSelecionada;
	private List<Sabor> sabores;
	private PrecoSabor configuracaoValores;
	private Pedido pedidoEdicao;
	private JButton btnFinalizar;


	/**
	 * Create the frame.
	 */
	public NovoPedido(List<Cliente> listaClientes, List<Sabor> sabores, PrecoSabor configuracaoValores, Pedido pedidoEdicao) {
		setResizable(false);
		setTitle("Pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 389, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboCliente = new JComboBox();
		comboCliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				preencherCliente(e);
			}
		});
		comboCliente.setBounds(62, 47, 164, 20);
		contentPane.add(comboCliente);
		
		JLabel lblNovoPedido = new JLabel("Novo Pedido");
		lblNovoPedido.setHorizontalAlignment(SwingConstants.LEFT);
		lblNovoPedido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNovoPedido.setBounds(10, 11, 296, 19);
		contentPane.add(lblNovoPedido);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 47, 46, 20);
		contentPane.add(lblCliente);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setBounds(10, 81, 99, 14);
		contentPane.add(lblNomeCompleto);
		
		txtNomeCompleto = new JTextField();
		txtNomeCompleto.setEditable(false);
		txtNomeCompleto.setBounds(110, 78, 253, 20);
		contentPane.add(txtNomeCompleto);
		txtNomeCompleto.setColumns(10);
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTabela.setLayout(null);
		panelTabela.setBounds(10, 144, 353, 207);
		contentPane.add(panelTabela);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 234, 157);
		panelTabela.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAtualizar.setEnabled(true);
				btnExcluir.setEnabled(true);
				selecionarPizzaEditar(e);
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(tabelaPizza);
		
		JLabel lblValorTotal = new JLabel("Valor Total");
		lblValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTotal.setBounds(254, 162, 68, 14);
		panelTabela.add(lblValorTotal);
		
		btnNovo = new JButton("Nova Pizza");
		btnNovo.setEnabled(false);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamarTelaPizza(false);
			}
		});
		btnNovo.setBounds(10, 10, 129, 23);
		panelTabela.add(btnNovo);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chamarTelaPizza(true);
			}
		});
		btnAtualizar.setEnabled(false);
		btnAtualizar.setBounds(254, 39, 89, 23);
		panelTabela.add(btnAtualizar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirPizza();
				btnExcluir.setEnabled(false);
				btnAtualizar.setEnabled(false);
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(254, 73, 89, 23);
		panelTabela.add(btnExcluir);
		
		lblTotal = new JLabel("R$ 0,00");
		lblTotal.setBounds(264, 182, 79, 14);
		panelTabela.add(lblTotal);
		
		
		txtFiltro = new JTextField();
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarTelefones();
			}
		});
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(236, 47, 86, 20);
		contentPane.add(txtFiltro);
		
		btnFinalizar = new JButton("Finalizar Pedido");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizarPedido();
			}
		});
		btnFinalizar.setEnabled(false);
		btnFinalizar.setBounds(10, 372, 129, 23);
		contentPane.add(btnFinalizar);
		
		this.sabores = sabores;
		this.configuracaoValores = configuracaoValores;
		
		this.pedidoEdicao = pedidoEdicao;
		if(pedidoEdicao != null) {
			preencherTelaEdicao(pedidoEdicao);
		}else {
			this.listaDeClientes = listaClientes;
			popularListaTelefones(listaClientes);
		}
	}

	private void preencherTelaEdicao(Pedido pedidoEdicao) {
		popularDadosCliente(pedidoEdicao);
		btnNovo.setEnabled(true);
		tabelaPizza.atualizarTabela(pedidoEdicao.getPizzas());
		lblTotal.setText("R$ " + Util.formatarDecimal(pedidoEdicao.getValorTotal()).toString());
	}

	private void popularDadosCliente(Pedido pedidoEdicao) {
		comboCliente.setEnabled(false);
		clienteSelecionado = pedidoEdicao.getCliente();
		comboCliente.setSelectedItem(clienteSelecionado.getTelefone());
		txtNomeCompleto.setText(clienteSelecionado.getNomeCompleto());
		txtFiltro.setEnabled(false);
	}

	private void popularListaTelefones(List<Cliente> listaClientes) {
		this.listaTelefone = listaClientes.stream().map(x -> x.getTelefone()).collect(Collectors.toList());
		this.listaTelefone.add(0,"Selecione");
		comboCliente.setModel(new DefaultComboBoxModel(this.listaTelefone.toArray()));
	}
	
	private void filtrarTelefones() {
		String valor = txtFiltro.getText();
		List<String> listaFiltrada = this.listaTelefone;
		if(!valor.isBlank()) {
			listaFiltrada = listaTelefone.stream().filter(t -> t.contains(valor)).collect(Collectors.toList());
			listaFiltrada.add(0,"Selecione");
		}
		comboCliente.setModel(new DefaultComboBoxModel(listaFiltrada.toArray()));
	}
	
	private void preencherCliente(ItemEvent e) {
		txtNomeCompleto.setText("");
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String telefone = e.getItem().toString();
			if(telefone != null && !telefone.isBlank() && !telefone.equals("Selecione")) {
				clienteSelecionado = listaDeClientes.stream().filter(c -> c.getTelefone().equals(telefone)).findAny().orElse(null);
				txtNomeCompleto.setText(clienteSelecionado.getNomeCompleto());
				btnNovo.setEnabled(true);
				//Atualizar tabela com pedidos
				Pedido pedido = clienteSelecionado.getPedido();
				atualizarTabelaPizzas(pedido);
			}else {
				btnNovo.setEnabled(false);
			}
	    }
	}

	private void atualizarTabelaPizzas(Pedido pedido) {
		if(pedido != null) {					
			tabelaPizza.atualizarTabela(pedido.getPizzas());
			atualizarPedido(pedido);
		}else {
			tabelaPizza.atualizarTabela(new ArrayList<Pizza>());
		}
	}
	
	private void atualizarPedido(Pedido pedido) {
		Double valorPizza = pedido.getPizzas().stream().mapToDouble(p -> p.getValor()).sum();
		pedido.setValorTotal(valorPizza);
		lblTotal.setText("R$ " + Util.formatarDecimal(pedido.getValorTotal()).toString());
		
		if(valorPizza > 0) btnFinalizar.setEnabled(true);
	}
	
	
	private void chamarTelaPizza(boolean isEdicao) {
		Pizza pizzaEdicao = isEdicao ? pizzaSelecionada : null;
		NovaPizza telaNovaPizza = new NovaPizza(clienteSelecionado, this.sabores, this.configuracaoValores, pizzaEdicao);
		telaNovaPizza.setVisible(true);
		telaNovaPizza.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				comboCliente.setEnabled(false);
				Pedido pedido = clienteSelecionado.getPedido();
				atualizarTabelaPizzas(pedido);
				btnAtualizar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnFinalizar.setEnabled(true);
			}
		});
		
	}
	
	private void selecionarPizzaEditar(MouseEvent evt) {
		int pizzaSelecionada = table.rowAtPoint(evt.getPoint());
        this.pizzaSelecionada = clienteSelecionado.getPedido().getPizzas().get(pizzaSelecionada);
	}
	
	private void excluirPizza() {
		clienteSelecionado.getPedido().getPizzas().remove(this.pizzaSelecionada);
		tabelaPizza.removePizza(pizzaSelecionada);
		atualizarPedido(clienteSelecionado.getPedido());
	}
	
	private void finalizarPedido() {
		Pedido pedido = clienteSelecionado.getPedido();
		pedido.setStatus(Status.ABERTO);
		this.dispose();
	}
	
}
