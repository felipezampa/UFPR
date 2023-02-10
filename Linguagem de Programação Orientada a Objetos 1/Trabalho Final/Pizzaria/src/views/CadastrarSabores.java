package views;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.EtchedBorder;

import model.pedido.Cliente;
import model.sabor.Sabor;
import model.tabela.ClienteTable;
import model.tabela.SaborTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import model.sabor.Tipo;

public class CadastrarSabores extends JFrame {

	private JPanel contentPane;
	private JTextField txtSabor;
	private JComboBox comboTipo;
	
	SaborTable saborTable = new SaborTable();
	int linhaSelecionada = -1;
	private JTable tableSabores;
	/**
	 * Create the frame.
	 */
	public CadastrarSabores(List<Sabor> sabores) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 303, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDados = new JPanel();
		panelDados.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDados.setBounds(10, 50, 266, 70);
		panelDados.setLayout(null);
		contentPane.add(panelDados);
		
		JLabel lblSabor = new JLabel("Sabor:");
		lblSabor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSabor.setBounds(10, 9, 64, 20);
		panelDados.add(lblSabor);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(6, 40, 68, 20);
		panelDados.add(lblTipo);
		
		comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(Tipo.values()));
		comboTipo.setEditable(true);
		comboTipo.setBounds(84, 39, 137, 20);
		panelDados.add(comboTipo);
		
		txtSabor = new JTextField();
		txtSabor.setBounds(84, 9, 137, 20);
		panelDados.add(txtSabor);
		txtSabor.setColumns(10);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirSabor();
			}
		});
		btnIncluir.setBounds(10, 131, 70, 23);
		contentPane.add(btnIncluir);
		
		JLabel lblTitulo = new JLabel("Cadastro Sabores");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(10, 11, 252, 19);
		contentPane.add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 208, 266, 125);
		contentPane.add(scrollPane);
		
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarSabor();
			}
		});
		btnAtualizar.setEnabled(false);
		btnAtualizar.setBounds(10, 174, 93, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirSabor();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(111, 174, 81, 23);
		contentPane.add(btnExcluir);
		
		tableSabores = new JTable();
		tableSabores.setModel(saborTable);
		tableSabores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAtualizar.setEnabled(true);
				btnExcluir.setEnabled(true);
				preencherEdicaoSabor(e);
			}
		});
		scrollPane.setViewportView(tableSabores);
		
		saborTable.atualizarTabela(sabores);
	}
	
	private void incluirSabor() {
        String nome = txtSabor.getText();
        String tipoSabor = comboTipo.getSelectedItem().toString();
        Sabor sabor = new Sabor(nome,tipoSabor);
        saborTable.adicionarSabor(sabor);		
	}
	
	private void preencherEdicaoSabor(MouseEvent evt) 
	{
		linhaSelecionada = tableSabores.rowAtPoint(evt.getPoint());
        Sabor sabor = saborTable.getSabor(linhaSelecionada);
        txtSabor.setText(sabor.getNome());
        comboTipo.setSelectedItem(sabor.getTipo());
	}
	
    private void alterarSabor() {
        if(linhaSelecionada!=-1){
            Sabor sabor = saborTable.getSabor(linhaSelecionada);
            sabor.setNome(txtSabor.getText());
            sabor.setTipo(comboTipo.getSelectedItem().toString());
            saborTable.fireTableRowsUpdated(linhaSelecionada, linhaSelecionada); 
        }
    }

    private void excluirSabor() {
        int[] linhasSelecionadas = tableSabores.getSelectedRows();
        List<Sabor> listaExcluir = new ArrayList();
        for (int i = 0; i < linhasSelecionadas.length; i++) {
        	Sabor sabor = saborTable.getSabor(linhasSelecionadas[i]);
            listaExcluir.add(sabor);

        }
        for(Sabor saborRemover:listaExcluir){
            saborTable.removeSabor(saborRemover);
        }
    }
}
