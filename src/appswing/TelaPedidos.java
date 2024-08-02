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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Pedido;
import regras_negocio.Fachada;

public class TelaPedidos {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;

	/**
	 * Launch the application.
	 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TelaPedidos tela = new TelaPedidos();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	/**
	 * Create the application.
	 */
	public TelaPedidos() {
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
		frame.setTitle("Pedidos");
		frame.setBounds(100, 100, 729, 419);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() >= 0) 
					label_6.setText("selecionado=" + table.getValueAt(table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(new Color(144, 238, 144));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel(""); // label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(12, 355, 688, 14);
		frame.getContentPane().add(label);

		label_6 = new JLabel("resultados:");
		label_6.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_6);

		label_1 = new JLabel("Nome Cliente:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(12, 269, 89, 14);
		frame.getContentPane().add(label_1);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(103, 266, 195, 20);
		frame.getContentPane().add(textField);

		button_1 = new JButton("Criar novo pedido");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField.getText().isEmpty() || textField_1.getText().isEmpty()
							|| textField_2.getText().isEmpty() || textField_3.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nomeCli = textField.getText();
					String quentinhaDescricao = textField_1.getText();
					String tamanho = textField_2.getText();
					String data = textField_3.getText();

					Fachada.cadastrarPedido(nomeCli, quentinhaDescricao, tamanho, data);
					label.setText("pedido criado");
					listagem();
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(281, 324, 153, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(308, 11, 89, 23);
		frame.getContentPane().add(button);

		label_2 = new JLabel("Descricao quentinha");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(310, 269, 101, 14);
		frame.getContentPane().add(label_2);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(414, 266, 168, 20);
		frame.getContentPane().add(textField_1);

		button_2 = new JButton("Deletar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						int id = (int) table.getValueAt(table.getSelectedRow(), 0);

						Fachada.excluirPedido(id);
						label.setText("pedido apagado");
						listagem();

					} else
						label.setText("nao selecionado");
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(229, 215, 171, 23);
		frame.getContentPane().add(button_2);

		textField_2 = new JTextField();
		textField_2.setBounds(113, 297, 130, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(47, 308, 1, 16);
		frame.getContentPane().add(textPane);

		label_3 = new JLabel("Tamanho");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(12, 295, 89, 16);
		frame.getContentPane().add(label_3);

		label_4 = new JLabel("Data");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(253, 297, 116, 16);
		frame.getContentPane().add(label_4);

		textField_3 = new JTextField();
		textField_3.setBounds(345, 294, 130, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		frame.setVisible(true);
	}

	public void listagem() {
		try {
			// ler os carros do banco
			List<Pedido> lista = Fachada.listarPedidos();

			// o model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			// adicionar colunas no model
			model.addColumn("id");
			model.addColumn("nome cliente");
			model.addColumn("descricao quentinha");
			model.addColumn("tamanho");
			model.addColumn("data");

			// adicionar linhas no model
			for (Pedido p : lista) {
				model.addRow(new Object[] { p.getId(),
						p.getCliente().getNome(), p.getQuentinha().getDescricao(), p.getTamanho(),
						p.getData() });
			}

			// atualizar model no table (visualizacao)
			table.setModel(model);

			label_6.setText("resultados: " + lista.size() + " objetos");
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

}
