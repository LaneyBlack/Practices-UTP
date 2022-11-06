package UTP43;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@FunctionalInterface
public interface MousePressListener extends MouseListener {
    @Override
    void mouseClicked(MouseEvent e); //the only method to override

    @Override
    default void mousePressed(MouseEvent e) {}

    @Override
    default void mouseReleased(MouseEvent e) {}

    @Override
    default void mouseEntered(MouseEvent e) {}

    @Override
    default void mouseExited(MouseEvent e) {}
}
