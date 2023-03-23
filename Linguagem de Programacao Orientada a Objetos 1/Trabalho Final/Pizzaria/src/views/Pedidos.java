package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;

import model.pedido.Cliente;
import model.pedido.Pedido;
import model.tabela.PedidoTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import model.pedido.Status;
import model.sabor.PrecoSabor;
import model.sabor.Sabor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class Pedidos extends JFrame {

	private JPanel contentPane;
	private JTable tablePedidos;
	JButton btnEditarPedido;
	JButton btnAtualizar;
	private JLabel lblNome;
	private JComboBox comboCliente;
	private PedidoTable pedidoTable = new PedidoTable();
	
	private List<Cliente> clientes;
	private List<Sabor> sabores;
	private PrecoSabor configPreco;
	
	private Pedido pedidoSelecionado;
	
	private int linhaSelecionada;

	/**
	 * Create the frame.
	 */
	public Pedidos(List<Cliente> clientes, List<Sabor> sabores, PrecoSabor configPreco) {
		setTitle("Pedidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPedidos = new JLabel("Pedidos");
		lblPedidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblPedidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPedidos.setBounds(10, 11, 230, 19);
		contentPane.add(lblPedidos);
		
		JPanel panelTabela = new JPanel();
		panelTabela.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelTabela.setLayout(null);
		panelTabela.setBounds(12, 41, 412, 207);
		contentPane.add(panelTabela);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 392, 157);
		panelTabela.add(scrollPane);
		
		tablePedidos = new JTable();
		tablePedidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencherEdicaoCliente(e);
				btnAtualizar.setEnabled(true);
				btnEditarPedido.setEnabled(true);
			}
		});
		scrollPane.setViewportView(tablePedidos);
		tablePedidos.setModel(pedidoTable);
		
		btnEditarPedido = new JButton("Editar Pedido");
		btnEditarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaEdicaoPedido();
			}
		});
		btnEditarPedido.setBounds(262, 179, 140, 23);
		panelTabela.add(btnEditarPedido);
		btnEditarPedido.setEnabled(false);
		
		JLabel lblPedido = new JLabel("Cliente:");
		lblPedido.setBounds(10, 183, 57, 14);
		panelTabela.add(lblPedido);
		lblPedido.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblNome = new JLabel("");
		lblNome.setBounds(71, 183, 181, 14);
		panelTabela.add(lblNome);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});
		btnAtualizar.setBounds(235, 258, 89, 23);
		contentPane.add(btnAtualizar);
		btnAtualizar.setEnabled(false);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(22, 259, 59, 20);
		contentPane.add(lblStatus);
		
		comboCliente = new JComboBox();
		comboCliente.setModel(new DefaultComboBoxModel(Status.values()));
		comboCliente.setBounds(88, 259, 137, 20);
		contentPane.add(comboCliente);
		
		this.clientes = clientes;
		this.sabores = sabores;
		this.configPreco = configPreco;
		
		listarTabelaPedido();
	}
	
	
	private void listarTabelaPedido() {
		var listaPedidos = this.clientes.stream().filter(p -> p.getPedido() != null)
				.map(m -> m.getPedido())
				.collect(Collectors.toList());
		pedidoTable.atualizarTabela(listaPedidos);
	}
	
	private void preencherEdicaoCliente(MouseEvent evt) 
	{
		linhaSelecionada = tablePedidos.rowAtPoint(evt.getPoint());
        pedidoSelecionado = pedidoTable.getPedido(linhaSelecionada);
        lblNome.setText(pedidoSelecionado.getCliente().getNomeCompleto());
        comboCliente.setSelectedItem(pedidoSelecionado.getStatus());
	}
	
    private void alterarCliente() {
        if(linhaSelecionada!=-1){
            Pedido cliente = pedidoTable.getPedido(linhaSelecionada);
            cliente.setStatus((Status)comboCliente.getSelectedItem());
            pedidoTable.fireTableRowsUpdated(linhaSelecionada, linhaSelecionada); 
        }
    }
    
    private void abrirTelaEdicaoPedido(){
    	NovoPedido editarPedido = new NovoPedido(null, this.sabores, this.configPreco, this.pedidoSelecionado);
    	editarPedido.setVisible(true);
    	editarPedido.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				listarTabelaPedido();
			}
		});
    }

}
