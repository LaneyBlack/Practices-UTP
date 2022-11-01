package UTP43;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MousePressListener extends ActionListener, MouseListener {

    @Override
    void actionPerformed(ActionEvent e);

    @Override
    void mouseClicked(MouseEvent e);

    @Override
    void mousePressed(MouseEvent e);

    @Override
    void mouseReleased(MouseEvent e);

    @Override
    void mouseEntered(MouseEvent e);

    @Override
    void mouseExited(MouseEvent e);

    default void event(MousePressListener e){

    }
}
