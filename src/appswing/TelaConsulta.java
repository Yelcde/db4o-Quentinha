/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Quentinha;
import regras_negocio.Fachada;

public class TelaConsulta {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JLabel label;
	private JLabel label_4;

	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaConsulta();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaConsulta() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Consulta");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		button = new JButton("Consultar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index<0)
					label_4.setText("consulta nao selecionada");
				else {
					label_4.setText("");
					switch(index) {
					case 0: 
						String data = JOptionPane.showInputDialog("digite a data");
						List<Pedido> resultado1 = Fachada.pedidosNaData(data);
						listagemPedidos(resultado1);
						break;
					case 1: 
						String cliente = JOptionPane.showInputDialog("digite o nome do cliente");
						List<Quentinha> resultado2 = Fachada.quentinhasDoCliente(cliente);
						listagemQuentinha(resultado2);
						break;
					case 2: 
						String n = JOptionPane.showInputDialog("digite N");
						int numero = Integer.parseInt(n);
						List<Cliente> resultado3 = Fachada.clientesMaisDeNPedidos(numero);
						listagemClientes(resultado3);
						break;

					}
				}

			}
		});
		button.setBounds(606, 10, 89, 23);
		frame.getContentPane().add(button);

		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("selecione a consulta");
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"pedidos na data X", "quentinha de cliente X", "clientes que possuem N pedidos"}));
		comboBox.setBounds(21, 10, 513, 22);
		frame.getContentPane().add(comboBox);
	}

	public void listagemPedidos(List<Pedido> lista) {
		try{
			// o model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("id");
			model.addColumn("nome cliente");
			model.addColumn("descricao quentinha");
			model.addColumn("tamanho");
			model.addColumn("data");

			//adicionar linhas no model
			for (Pedido p : lista) {
				model.addRow(new Object[] { p.getId(),
						p.getCliente().getNome(), p.getQuentinha().getDescricao(), p.getTamanho(),
						p.getData() });
			}
			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
	
	public void listagemQuentinha(List<Quentinha> lista) {
		try{
			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("descricao");
			model.addColumn("preco");

			//adicionar linhas no model
			for(Quentinha quentinha : lista) {
				model.addRow(new Object[]{quentinha.getDescricao(), quentinha.getPreco()} );
			}
			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
	
	public void listagemClientes(List<Cliente> lista) {
		try{
			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("nome");
			model.addColumn("telefone");

			//adicionar linhas no model
			for(Cliente cli : lista) {
				model.addRow(new Object[]{cli.getNome(), cli.getTelefone()} );
			}
			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}

}
