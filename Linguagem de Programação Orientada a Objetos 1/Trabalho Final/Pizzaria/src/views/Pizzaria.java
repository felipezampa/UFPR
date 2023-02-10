package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.pedido.Cliente;
import model.sabor.PrecoSabor;
import model.sabor.Sabor;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import java.awt.SystemColor;

public class Pizzaria extends JFrame {

	private JPanel contentPane;
	
	private final List<Cliente> clientes = new ArrayList<Cliente>();
	private final List<Sabor> sabores = new ArrayList<Sabor>();
	private PrecoSabor configuracaoValores = new PrecoSabor(0,0,0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pizzaria frame = new Pizzaria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pizzaria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaClientes();
			}
		});
		menuBar.add(mnClientes);
		
		JMenuItem mnRegistroClientes = new JMenuItem("Registro Clientes");
		mnRegistroClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaClientes();
			}
		});
		mnClientes.add(mnRegistroClientes);
		
		JMenu mnPedido = new JMenu("Pedidos");
		menuBar.add(mnPedido);
		
		JMenuItem mnNovoPedido = new JMenuItem("Novo Pedido");
		mnNovoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaNovoPedido();
			}
		});
		mnPedido.add(mnNovoPedido);
		
		JMenuItem mnAcompPedidos = new JMenuItem("Acompanhar Pedidos");
		mnAcompPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaPedidos();
			}
		});
		mnPedido.add(mnAcompPedidos);
		
		JMenu mnConfig = new JMenu("Configura\u00E7\u00F5es");
		menuBar.add(mnConfig);
		
		JMenuItem mnPreco = new JMenuItem("Pre\u00E7o sabores");
		mnPreco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaConfiguracao();
			}
		});
		mnConfig.add(mnPreco);
		
		JMenuItem mnSabores = new JMenuItem("Registrar Sabores");
		mnSabores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTelaSabores();
			}
		});
		mnConfig.add(mnSabores);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	private void abrirTelaClientes() {
		ClienteCadastro tela = new ClienteCadastro(clientes); //Passar lista parâmetro
		tela.setVisible(true);
	}
	
	private void abrirTelaNovoPedido() {
		NovoPedido tela = new NovoPedido(clientes, sabores, configuracaoValores,null); //Passar lista parâmetro
		tela.setVisible(true);
	}
	
	private void abrirTelaPedidos() {
		Pedidos tela = new Pedidos(clientes, sabores, configuracaoValores); //Passar lista parâmetro
		tela.setVisible(true);
	}
	
	private void abrirTelaConfiguracao() {
		ConfiguracaoPreco tela = new ConfiguracaoPreco(configuracaoValores); //Passar lista parâmetro
		tela.setVisible(true);
	}
	
	private void abrirTelaSabores() {
		CadastrarSabores tela = new CadastrarSabores(sabores); //Passar lista parâmetro
		tela.setVisible(true);
	}
}
