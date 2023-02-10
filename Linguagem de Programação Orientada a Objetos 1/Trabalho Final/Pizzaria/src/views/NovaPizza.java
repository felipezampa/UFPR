package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;

import model.formatos.Circular;
import model.formatos.Formato;
import model.formatos.Pizza;
import model.formatos.Quadrada;
import model.formatos.Triangular;
import model.pedido.Cliente;
import model.pedido.Pedido;
import model.sabor.PrecoSabor;
import model.sabor.Sabor;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NovaPizza extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMedida;
	private JTextField txtArea;
	private JComboBox comboForma;
	private JComboBox comboSabor1;
	private JComboBox comboSabor2;
	
	private PrecoSabor configPreco;
	private List<Sabor> sabores = new ArrayList<>();
	private Pedido pedido;
	private Pizza pizzaEdicao;
	
	/**
	 * Create the dialog.
	 */
	public NovaPizza(Cliente cliente, List<Sabor> sabores, PrecoSabor configPreco, Pizza pizzaEdicao) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Nova Pizza");
		setResizable(false);
		setBounds(100, 100, 366, 359);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			comboForma = new JComboBox();
			comboForma.setModel(new DefaultComboBoxModel(Formato.values()));
			comboForma.setEditable(true);
			comboForma.setBounds(103, 11, 137, 20);
			contentPanel.add(comboForma);
		}
		{
			JLabel lblFormaPizza = new JLabel("Forma Pizza:");
			lblFormaPizza.setBounds(10, 11, 83, 20);
			contentPanel.add(lblFormaPizza);
		}
		{
			JPanel panelDados = new JPanel();
			panelDados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panelDados.setLayout(null);
			panelDados.setBounds(10, 58, 303, 81);
			contentPanel.add(panelDados);
			{
				JLabel lblMedida = new JLabel("Medida(cm):");
				lblMedida.setHorizontalAlignment(SwingConstants.RIGHT);
				lblMedida.setBounds(10, 8, 78, 20);
				panelDados.add(lblMedida);
			}
			{
				txtMedida = new JTextField();
				txtMedida.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						calcularArea();
					}
				});
				txtMedida.setColumns(10);
				txtMedida.setBounds(98, 8, 131, 20);
				panelDados.add(txtMedida);
			}
			{
				JLabel lblrea = new JLabel("\u00C1rea(cm2):");
				lblrea.setHorizontalAlignment(SwingConstants.RIGHT);
				lblrea.setBounds(10, 50, 78, 20);
				panelDados.add(lblrea);
			}
			{
				txtArea = new JTextField();
				txtArea.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						calcularMedida();
					}
				});
				txtArea.setColumns(10);
				txtArea.setBounds(98, 50, 131, 20);
				panelDados.add(txtArea);
			}
			
			JSeparator separator = new JSeparator();
			separator.setBounds(20, 39, 250, 1);
			panelDados.add(separator);
		}
		{
			JLabel lblDimensoes = new JLabel("Dimens\u00F5es");
			lblDimensoes.setBounds(10, 42, 69, 14);
			contentPanel.add(lblDimensoes);
		}
		
		JLabel lblSabores = new JLabel("Sabores");
		lblSabores.setBounds(10, 163, 69, 14);
		contentPanel.add(lblSabores);
		
		JPanel panelDados = new JPanel();
		panelDados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDados.setLayout(null);
		panelDados.setBounds(10, 179, 303, 68);
		contentPanel.add(panelDados);
		
		JLabel lblSabor = new JLabel("Sabor 1:");
		lblSabor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSabor.setBounds(10, 8, 78, 20);
		panelDados.add(lblSabor);
		
		JLabel lblSabor_1 = new JLabel("Sabor 2:");
		lblSabor_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSabor_1.setBounds(10, 40, 78, 20);
		panelDados.add(lblSabor_1);
		
		comboSabor1 = new JComboBox();
		comboSabor1.setEditable(true);
		comboSabor1.setBounds(98, 7, 137, 20);
		panelDados.add(comboSabor1);
		
		comboSabor2 = new JComboBox();
		comboSabor2.setEditable(true);
		comboSabor2.setBounds(98, 39, 137, 20);
		panelDados.add(comboSabor2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Incluir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						incluirPizzaPedido();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		this.sabores = sabores;
	    List<String> saboresFiltrados = sabores.stream().map(x -> x.getNome()).collect(Collectors.toList());
	    comboSabor1.setModel(new DefaultComboBoxModel(saboresFiltrados.toArray()));
	    saboresFiltrados.add(0, "Selecione");
	    comboSabor2.setModel(new DefaultComboBoxModel(saboresFiltrados.toArray()));
	    
		
		if(cliente.getPedido() != null) {			
			this.pedido = cliente.getPedido(); 
		}else {
			Pedido novoPedido = new Pedido();
			novoPedido.setCliente(cliente);
			cliente.setPedido(novoPedido);
			this.pedido = cliente.getPedido();
		}
		
		this.configPreco = configPreco;
		this.pizzaEdicao = pizzaEdicao;
		
		if(pizzaEdicao != null) {
			preencherTelaEdicao(pizzaEdicao);			
		}
	}

	private void preencherTelaEdicao(Pizza pizzaEdicao) {
		comboForma.setSelectedItem(tipoPizza(pizzaEdicao));
		comboForma.setEnabled(false);
		
		txtArea.setText(pizzaEdicao.getArea().toString());
		
		comboSabor1.setSelectedItem(pizzaEdicao.getSabores().get(0).getNome());
		
		if(pizzaEdicao.getSabores().size() == 2)
		comboSabor2.setSelectedItem(pizzaEdicao.getSabores().get(1).getNome());
	}
	
	private void incluirPizzaPedido() {
	
		try {
			Double valorArea = Double.parseDouble(txtArea.getText());
			String formatoSelecionado = comboForma.getSelectedItem().toString();
			Formato formato = Formato.valueOf(formatoSelecionado);
			
			boolean isEdicao = this.pizzaEdicao != null;
			
			if(isEdicao) {
				Pizza pizza = this.pizzaEdicao;
				pizza.setArea(valorArea);
				List<Sabor> listaSaboresSelecionados = recuperarSaboresSelecionados();
				pizza.setSabores(listaSaboresSelecionados);
				pizza.calcularPreco(configPreco);	
			}else {
				Pizza pizza = criarPizza(formato);
				pizza.setArea(valorArea);
				List<Sabor> listaSaboresSelecionados = recuperarSaboresSelecionados();
				pizza.setSabores(listaSaboresSelecionados);
				pizza.calcularPreco(configPreco);
				pedido.setPizza(pizza);	
			}
			
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Digite parâmetros válidos","Erro", JOptionPane.ERROR_MESSAGE);
		}
		this.dispose();
	}

	private List<Sabor> recuperarSaboresSelecionados() {
		List<Sabor> listaSabores = new ArrayList<>();
		String nomeSabor = comboSabor1.getSelectedItem().toString();
		Sabor primeiroSabor = retornarSabor(nomeSabor);
		
		listaSabores.add(primeiroSabor);
		
		nomeSabor = comboSabor2.getSelectedItem().toString();
		Sabor segundoSabor = retornarSabor(nomeSabor);
		
		if(segundoSabor != null) listaSabores.add(segundoSabor);	
		
		return listaSabores; 
	}

	private Sabor retornarSabor(String nomeSabor) {
		return sabores.stream().filter(s -> s.getNome().equals(nomeSabor)).findAny().orElse(null);
	}
	

	
	private void calcularArea() {
		try {
			if (!txtMedida.getText().isBlank()) 
			{
				String formatoSelecionado = comboForma.getSelectedItem().toString();
				Formato formato = Formato.valueOf(formatoSelecionado);
				Double valorMedida = Double.parseDouble(txtMedida.getText());
				Pizza pizza = criarPizza(formato);
				Double valorArea = pizza.calcularArea(valorMedida);
				if (pizza.validarArea(valorArea)) {
					txtArea.setText(valorArea.toString());
				} else {
					JOptionPane.showMessageDialog(null, "Medida fora das dimensões recomendadas!", "Alerta",
							JOptionPane.ERROR_MESSAGE);
					txtArea.setText("");
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite valores númericos para a medida!","Erro", JOptionPane.ERROR_MESSAGE);
		} 	
	}
	
	private void calcularMedida() {
		try {
			if (!txtArea.getText().isBlank()) {
				String formatoSelecionado = comboForma.getSelectedItem().toString();
				Formato formato = Formato.valueOf(formatoSelecionado);
				Pizza pizza = criarPizza(formato);
				Double valorArea = Double.parseDouble(txtArea.getText());
				Double valorMedida = pizza.calcularMedida(valorArea);
				if (pizza.validarMedida(valorMedida)) {
					txtMedida.setText(valorMedida.toString());
				} else {
					JOptionPane.showMessageDialog(null, "Área fora das dimensões recomendadas!", "Alerta",
							JOptionPane.ERROR_MESSAGE);
					txtMedida.setText("");
				} 
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite valores númericos para a área!","Erro", JOptionPane.ERROR_MESSAGE);
		} 	
	}
	
	private Pizza criarPizza(Formato formato) {
		Pizza pizza = null;
		switch(formato) {
			case QUADRADO:
				pizza = new Quadrada();
				break;
			case TRIANGULAR:
				pizza = new Triangular();
				break;
			case CIRCULAR:
				pizza = new Circular();
				break;
		}
		return pizza;
	}
	
	private Formato tipoPizza(Pizza pizza) {
		if(pizza instanceof Circular) return Formato.CIRCULAR;
		if(pizza instanceof Quadrada) return Formato.QUADRADO;
		if(pizza instanceof Triangular) return Formato.TRIANGULAR;
		return Formato.QUADRADO;
	}
}
