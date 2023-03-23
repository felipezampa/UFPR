package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

import model.sabor.PrecoSabor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfiguracaoPreco extends JFrame {

	private JPanel contentPane;
	private JTextField txtSimples;
	private JTextField txtEspecial;
	private JTextField txtPremium;
	
	private PrecoSabor configuracaoPreco;

	/**
	 * Create the frame.
	 */
	public ConfiguracaoPreco(PrecoSabor configuracaoPreco) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 280, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDados = new JPanel();
		panelDados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDados.setLayout(null);
		panelDados.setBounds(10, 45, 250, 96);
		contentPane.add(panelDados);
		
		JLabel lblSimples = new JLabel("Simples");
		lblSimples.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSimples.setBounds(10, 8, 66, 20);
		panelDados.add(lblSimples);
		
		txtSimples = new JTextField();
		txtSimples.setColumns(10);
		txtSimples.setBounds(86, 8, 131, 20);
		panelDados.add(txtSimples);
		
		JLabel lblEspecial = new JLabel("Especial");
		lblEspecial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspecial.setBounds(10, 38, 66, 20);
		panelDados.add(lblEspecial);
		
		txtEspecial = new JTextField();
		txtEspecial.setColumns(10);
		txtEspecial.setBounds(86, 38, 131, 20);
		panelDados.add(txtEspecial);
		
		JLabel lblPremium = new JLabel("Premium");
		lblPremium.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPremium.setBounds(10, 69, 66, 20);
		panelDados.add(lblPremium);
		
		txtPremium = new JTextField();
		txtPremium.setColumns(10);
		txtPremium.setBounds(86, 69, 131, 20);
		panelDados.add(txtPremium);
		
		JLabel lblConfigurarValores = new JLabel("Configurar Valores");
		lblConfigurarValores.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfigurarValores.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfigurarValores.setBounds(10, 11, 414, 19);
		contentPane.add(lblConfigurarValores);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalvarValores();
			}
		});
		btnSalvar.setBounds(10, 153, 89, 23);
		contentPane.add(btnSalvar);
		
		this.configuracaoPreco = configuracaoPreco;
		txtSimples.setText(configuracaoPreco.getValorSimples().toString());
		txtEspecial.setText(configuracaoPreco.getValorEspecial().toString());
		txtPremium.setText(configuracaoPreco.getValorPremium().toString());
	}
	
	private void SalvarValores() {
		
		try {
			Double valorSimples = Double.parseDouble(txtSimples.getText());
			Double valorEspecial = Double.parseDouble(txtEspecial.getText());
			Double valorPremium = Double.parseDouble(txtPremium.getText());
			
			this.configuracaoPreco.setValorEspecial(valorEspecial);
			this.configuracaoPreco.setValorPremium(valorPremium);
			this.configuracaoPreco.setValorSimples(valorSimples);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite parâmetros válidos","Erro", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
