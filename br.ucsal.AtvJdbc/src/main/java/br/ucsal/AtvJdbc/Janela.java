package br.ucsal.AtvJdbc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Janela extends JFrame {
	Connection conexao;
	public Janela(Connection conexao) {
		super("JDBC");

		this.conexao = conexao;

		setSize(600,400);
		JLabel label = new JLabel("Resultado:");
		JTextField jtext = new JTextField(30);
		JButton button = new JButton("Executar");

		final JTextArea textArea = new JTextArea();

		JPanel top = new JPanel();
		top.add(label);
		top.add(jtext);
		top.add(button);

		this.add(top, BorderLayout.NORTH);

		this.add(textArea);

		this.setVisible(true);

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Statement stmt;
				try {
					stmt = Janela.this.conexao.createStatement();


					ResultSet cursor = stmt.executeQuery("SELECT "
							+"matricula, nome FROM alunos WHERE matricula "
							+"like '2005%'");
					
					StringBuffer valor = new StringBuffer();
					valor.append("MATRICULA\tNOME\n");
					while(cursor.next()) {
						valor.append(cursor.getString("nome")+"\t");
						valor.append(cursor.getString("matricula")+"\n");
//						System.out.println("Nome: " + cursor.getString("nome"));
//						System.out.println("Matricula: " + cursor.getString("matricula")+"\n");
						
					}
					textArea.setText(valor.toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
