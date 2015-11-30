package view;

import controller.StudentPanelController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import model.Student;
import model.StudentPanelModel;
import model.XMLObject;
import view.Frame;


   
/**
@author Feek <feek@psu.edu>
**/

public class StudentPanel extends JPanel{
    public final Frame frame;
    public final ArrayList<JCheckBox> checkboxes;
    private final int X = 10;
    public int Y = 10;
    public final int INITIAL_Y = 10; // used in cases of resetting students
    private final int Y_INCREMENT = 20; // space between boxes
    private final int WIDTH;
    public int HEIGHT; // adjusted when students are added
    public final int INITIAL_HEIGHT; // used in cases of resetting students
    public final int BUTTON_PADDING = 90; // used in cases of resetting students
    private JButton studentLocationButton;
    public JTextField studentFileLocationTextField;
    public JButton selectAll;
    public JButton deselectAll;
    private final StudentPanelModel model;
    
    Font myFont2 = new Font("Century Schoolbook", Font.PLAIN, 14);
    Border thickBorder = new LineBorder(Color.decode("#4B0082"), 3);
    
    public StudentPanel(Frame frame, XMLObject xmlObject) {
        this.frame = frame;
        this.model = new StudentPanelModel();
        this.checkboxes = new ArrayList<>();
        this.WIDTH = frame.WIDTH / 3;
        this.HEIGHT = frame.HEIGHT;
        this.INITIAL_HEIGHT = HEIGHT;
        this.setLayout(null);
        this.setBackground(Color.pink);
        initComponents();
        
        if(StudentPanelController.setDefaults(xmlObject, model)) {
            this.studentFileLocationTextField.setText(model.studentFileLocationAbsolutePath);
            initCheckboxes();
            enableSelectButtons();
        }
    }

    private void initComponents() {
        this.setSize(WIDTH, HEIGHT);
        initStudentLocationComponents();
        initSelectButtons();
        this.setVisible(true);
    }
    
    private void initSelectButtons() {
        selectAll = new JButton("Select All");
        deselectAll = new JButton("Deselect All");
        
        selectAll.setBounds(X, Y, 120, 30);
        selectAll.setFont(myFont2);
        selectAll.setBorder(thickBorder);
        deselectAll.setBounds(X + 130, Y, 120, 30);
        deselectAll.setFont(myFont2);
        
        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentPanelController.selectAllBoxes(checkboxes);
            }
        });
        deselectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentPanelController.deselectAllBoxes(checkboxes);
            }
        });
        
        add(selectAll);
        add(deselectAll);
        
        // at this time, no students have been added
        disableSelectButtons();
        
        Y += 40;
    }
    
    private void initStudentLocationComponents() {
        studentLocationButton = new JButton("Student File Location");
        studentLocationButton.setBounds(X, Y, 150, 30);
        studentLocationButton.setFont(myFont2);
        studentLocationButton.setBorder(thickBorder);
        
        final StudentPanel self = this;
        studentLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentPanelController.studentLocationButtonClicked(self, self.model);
            }
        });
        add(studentLocationButton);
        
        studentFileLocationTextField = new JTextField();
        studentFileLocationTextField.setBounds(X + 165, Y, 150, 30);
        studentFileLocationTextField.setEnabled(false);
        add(studentFileLocationTextField);
        
        Y += 40;
    }

    private void importStudents() {
        StudentPanelController.clearStudents(this, this.model);
        StudentPanelController.importStudents(model.studentFileLocationAbsolutePath, model.delimiter, model.students);
    }
    
    // loads in the students from the file and displays them along with checkboxes
    public void initCheckboxes() {
        importStudents();
        
        for(Student s : model.students) {
            JCheckBox box = new JCheckBox(s.getInfo());
            box.setAlignmentY(LEFT_ALIGNMENT);
            box.setBounds(X, Y, 200, 15);
            this.Y += Y_INCREMENT;
            this.add(box);
            checkboxes.add(box);
        }
        
        this.HEIGHT = Y; // update height of panel so scrolling can happen
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.revalidate();
    }
    
    public ArrayList<Student> getSelectedStudents() {
        return StudentPanelController.getSelectedStudents(this.checkboxes, model.students);
    }

    public void enableSelectButtons() {
        selectAll.setEnabled(true);
        deselectAll.setEnabled(true);
    }
    
    public void disableSelectButtons() {
        selectAll.setEnabled(false);
        deselectAll.setEnabled(false);
    }
    
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.decode("#60DFE5");
        Color color2 = Color.WHITE;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
