package view;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalculadoraPocoes extends JFrame {

    private JComboBox<String> comboTipoPocao;
    private JCheckBox chkMandragora, chkLagrimaFenix, chkPoLua, chkEssenciaDragao, chkPenaHipogrifo, chkEscamaSereia, chkPoEstrela, chkNectarFlorLunar, chkCarinhoSandra;
    private JSlider sliderPotencia;
    private JTextArea txtResultado;
    private JTextArea txtDescricao;
    private JProgressBar barraProgresso;

    private Map<String, String> descricaoPocoes;

    public CalculadoraPocoes() {
        setTitle("Grimório de Poções Antigas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 700);
        setLayout(null);

        descricaoPocoes = new LinkedHashMap<>();
        descricaoPocoes.put("Poção para tirar 10 na Av4 de Java", "✨ Poção lendária criada para conquistar os mais difíceis desafios acadêmicos. Requer um toque especial da mestra dos feitiços!✨");
        descricaoPocoes.put("Poção de Cura", "Restaura a energia vital do corpo ferido.");
        descricaoPocoes.put("Poção de Invisibilidade", "Oculta a presença do portador entre as sombras.");
        descricaoPocoes.put("Poção de Força", "Confere o vigor de dez homens por um breve instante.");
        descricaoPocoes.put("Poção de Velocidade", "Concede agilidade sobre-humana ao bebedor.");
        descricaoPocoes.put("Poção de Sorte", "Atrai boas energias e coincidências afortunadas.");
        descricaoPocoes.put("Poção de Sabedoria", "Clareia a mente e eleva o raciocínio.");
        descricaoPocoes.put("Poção de Resistência", "Cria um escudo mágico invisível contra danos externos.");

        JLabel lblTipo = new JLabel("Tipo de Poção:");
        lblTipo.setBounds(30, 20, 120, 20);
        add(lblTipo);

        String[] tipos = descricaoPocoes.keySet().toArray(new String[0]);
        comboTipoPocao = new JComboBox<>(tipos);
        comboTipoPocao.setBounds(150, 20, 300, 20);
        add(comboTipoPocao);

        txtDescricao = new JTextArea("Descrição: " + descricaoPocoes.get(tipos[0]));
        txtDescricao.setBounds(30, 50, 520, 40);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        txtDescricao.setEditable(false);
        txtDescricao.setOpaque(false);
        txtDescricao.setFocusable(false);
        txtDescricao.setBorder(null);
        add(txtDescricao);

        comboTipoPocao.addActionListener(e -> {
            String tipo = (String) comboTipoPocao.getSelectedItem();
            txtDescricao.setText("Descrição: " + descricaoPocoes.get(tipo));
        });

        JLabel lblIngredientes = new JLabel("Ingredientes:");
        lblIngredientes.setBounds(30, 100, 150, 20);
        add(lblIngredientes);

        chkMandragora = new JCheckBox("Raiz de Mandrágora");
        chkMandragora.setBounds(150, 100, 250, 20);
        add(chkMandragora);

        chkLagrimaFenix = new JCheckBox("Lágrima de Fênix");
        chkLagrimaFenix.setBounds(150, 130, 250, 20);
        add(chkLagrimaFenix);

        chkPoLua = new JCheckBox("Pó de Lua");
        chkPoLua.setBounds(150, 160, 250, 20);
        add(chkPoLua);

        chkEssenciaDragao = new JCheckBox("Essência de Dragão");
        chkEssenciaDragao.setBounds(150, 190, 250, 20);
        add(chkEssenciaDragao);

        chkPenaHipogrifo = new JCheckBox("Pena de Hipogrifo");
        chkPenaHipogrifo.setBounds(150, 220, 250, 20);
        add(chkPenaHipogrifo);

        chkEscamaSereia = new JCheckBox("Escama de Sereia");
        chkEscamaSereia.setBounds(150, 250, 250, 20);
        add(chkEscamaSereia);

        chkPoEstrela = new JCheckBox("Pó de Estrela");
        chkPoEstrela.setBounds(150, 280, 250, 20);
        add(chkPoEstrela);

        chkNectarFlorLunar = new JCheckBox("Néctar de Flor Lunar");
        chkNectarFlorLunar.setBounds(150, 310, 250, 20);
        add(chkNectarFlorLunar);

        chkCarinhoSandra = new JCheckBox("Carinho da Professora Sandra ❤️");
        chkCarinhoSandra.setBounds(150, 340, 300, 20);
        add(chkCarinhoSandra);

        JLabel lblPotencia = new JLabel("Potência da Poção:");
        lblPotencia.setBounds(30, 380, 150, 20);
        add(lblPotencia);

        sliderPotencia = new JSlider(1, 10, 5);
        sliderPotencia.setBounds(150, 380, 300, 50);
        sliderPotencia.setMajorTickSpacing(1);
        sliderPotencia.setPaintTicks(true);
        sliderPotencia.setPaintLabels(true);
        add(sliderPotencia);

        JButton btnCalcular = new JButton("Invocar Receita");
        btnCalcular.setBounds(150, 440, 150, 30);
        add(btnCalcular);

        JButton btnLimpar = new JButton("Apagar Traços Mágicos");
        btnLimpar.setBounds(310, 440, 200, 30);
        add(btnLimpar);

        barraProgresso = new JProgressBar(0, 100);
        barraProgresso.setBounds(30, 480, 520, 20);
        barraProgresso.setStringPainted(true);
        add(barraProgresso);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        scrollPane.setBounds(30, 510, 520, 150);
        add(scrollPane);

        btnCalcular.addActionListener(e -> prepararPocao());
        btnLimpar.addActionListener(e -> limparReceita());
    }

    private void prepararPocao() {
        barraProgresso.setValue(0);
        txtResultado.setText("");

        Timer timer = new Timer(50, null);
        timer.addActionListener(new ActionListener() {
            int progresso = 0;

            public void actionPerformed(ActionEvent e) {
                progresso += 5;
                barraProgresso.setValue(progresso);
                if (progresso >= 100) {
                    ((Timer) e.getSource()).stop();
                    calcularReceita();
                }
            }
        });
        timer.start();
    }

    private void calcularReceita() {
        String tipo = (String) comboTipoPocao.getSelectedItem();
        StringBuilder ingredientes = new StringBuilder();

        if (chkMandragora.isSelected()) ingredientes.append("Raiz de Mandrágora\n");
        if (chkLagrimaFenix.isSelected()) ingredientes.append("Lágrima de Fênix\n");
        if (chkPoLua.isSelected()) ingredientes.append("Pó de Lua\n");
        if (chkEssenciaDragao.isSelected()) ingredientes.append("Essência de Dragão\n");
        if (chkPenaHipogrifo.isSelected()) ingredientes.append("Pena de Hipogrifo\n");
        if (chkEscamaSereia.isSelected()) ingredientes.append("Escama de Sereia\n");
        if (chkPoEstrela.isSelected()) ingredientes.append("Pó de Estrela\n");
        if (chkNectarFlorLunar.isSelected()) ingredientes.append("Néctar de Flor Lunar\n");
        if (chkCarinhoSandra.isSelected()) ingredientes.append("Carinho da Professora Sandra ❤️\n");

        int potencia = sliderPotencia.getValue();

        StringBuilder resultado = new StringBuilder();
        resultado.append("\n>>Receita da " + tipo + " <<\n");

        resultado.append("\nIngredientes Mágicos:\n");
        if (ingredientes.length() > 0) {
            resultado.append(ingredientes);
        } else {
            resultado.append("(Nenhum ingrediente selecionado)\n");
        }

        resultado.append("\nPotência do Feitiço: [").append(potencia).append(" / 10]\n");
        if (potencia >= 8) {
            resultado.append("✧ Energia mágica em seu auge!\n");
        } else if (potencia <= 3) {
            resultado.append("(×_×) Poção de baixo poder... tente reforçar.\n");
        } else {
            resultado.append("☻ Poção equilibrada e segura.\n");
        }

        resultado.append("\n✨ Que os antigos espíritos o guiem. ✨\n");

        txtResultado.setText(resultado.toString());
    }

    private void limparReceita() {
        chkMandragora.setSelected(false);
        chkLagrimaFenix.setSelected(false);
        chkPoLua.setSelected(false);
        chkEssenciaDragao.setSelected(false);
        chkPenaHipogrifo.setSelected(false);
        chkEscamaSereia.setSelected(false);
        chkPoEstrela.setSelected(false);
        chkNectarFlorLunar.setSelected(false);
        chkCarinhoSandra.setSelected(false);
        sliderPotencia.setValue(5);
        barraProgresso.setValue(0);
        txtResultado.setText("");
    }

    public static void main(String[] args) {
        CalculadoraPocoes frame = new CalculadoraPocoes();
        frame.setVisible(true);
    }
}
