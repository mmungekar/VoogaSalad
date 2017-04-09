package authoring.canvas;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/**
 * Some code borrowed from
 * http://stackoverflow.com/questions/16925612/how-to-resize-component-with-mouse-drag-in-javafx
 * 
 * @author jimmy
 *
 */
public class DragResizer
{

	/**
	 * The margin around the control that a user can click in to start resizing
	 * the region.
	 */
	private static final int RESIZE_MARGIN = 15;
	private static final int TILE_SIZE = 25;

	private final EntityDisplay region;

	private double x;
	private double y;

	private boolean initMinWidth;
	private boolean initMinHeight;

	private boolean xDragging;
	private boolean yDragging;
	private boolean moveDragging;

	private DragResizer(EntityDisplay entityDisplay)
	{
		region = entityDisplay;
	}

	public static void makeResizable(EntityDisplay region)
	{
		final DragResizer resizer = new DragResizer(region);

		region.setOnMousePressed(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				resizer.mousePressed(event);
			}
		});
		region.setOnMouseDragged(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				resizer.mouseDragged(event);
			}
		});
		region.setOnMouseMoved(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				resizer.mouseOver(event);
			}
		});
		region.setOnMouseReleased(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				resizer.mouseReleased(event);
			}
		});
	}

	protected void mouseReleased(MouseEvent event)
	{
		xDragging = false;
		yDragging = false;
		moveDragging = false;
		region.setCursor(Cursor.DEFAULT);
	}

	protected void mouseOver(MouseEvent event)
	{
		if ((isInXDraggableZone(event) && isInYDraggableZone(event)) || (xDragging && yDragging)) {
			region.setCursor(Cursor.SE_RESIZE);
		} else if (isInXDraggableZone(event) || xDragging) {
			region.setCursor(Cursor.E_RESIZE);
		} else if (isInYDraggableZone(event) || yDragging) {
			region.setCursor(Cursor.S_RESIZE);
		} else if (isInMoveDraggableZone(event) && !moveDragging) {
			region.setCursor(Cursor.CLOSED_HAND);
		} else if (moveDragging) {
			region.setCursor(Cursor.NONE);
		} else {
			region.setCursor(Cursor.DEFAULT);
		}
	}

	private boolean isInXDraggableZone(MouseEvent event)
	{
		return event.getX() > (region.getWidth() - RESIZE_MARGIN);
	}

	private boolean isInYDraggableZone(MouseEvent event)
	{
		return event.getY() > (region.getHeight() - RESIZE_MARGIN);
	}

	private boolean isInMoveDraggableZone(MouseEvent event)
	{
		return event.getX() > 0 && event.getX() < region.getWidth() - RESIZE_MARGIN && event.getY() > 0
				&& event.getY() < region.getHeight() - RESIZE_MARGIN;
	}

	protected void mouseDragged(MouseEvent event)
	{
		if (xDragging) {

			double mousex = event.getX();

			double newWidth = region.getMinWidth() + (mousex - x);

			region.setMinWidth(newWidth);

			x = mousex;
		}

		if (yDragging) {

			double mousey = event.getY();

			double newHeight = region.getMinHeight() + (mousey - y);

			region.setMinHeight(newHeight);

			y = mousey;
		}

		if (moveDragging) {
			region.setCursor(Cursor.NONE);
			double mouseX = event.getX();
			double mouseY = event.getY();

			region.setTranslateX(getTiledCoordinate(region.getTranslateX() + mouseX - region.getWidth() / 2));
			region.setTranslateY(getTiledCoordinate(region.getTranslateY() + mouseY - region.getHeight() / 2));
		}
	}

	protected void mousePressed(MouseEvent event)
	{

		if (isInXDraggableZone(event)) {
			xDragging = true;

			if (!initMinWidth) {
				region.setMinWidth(region.getWidth());
				initMinWidth = true;
			}
			x = event.getX();
		}

		if (isInYDraggableZone(event)) {
			yDragging = true;

			if (!initMinHeight) {
				region.setMinHeight(region.getHeight());
				initMinHeight = true;
			}
			y = event.getY();
		}

		if (isInMoveDraggableZone(event)) {
			moveDragging = true;
			x = event.getX();
			y = event.getY();
		}
	}

	private double getTiledCoordinate(double coordinate)
	{
		double gridCoordinate = ((int) coordinate / TILE_SIZE) * TILE_SIZE;
		return gridCoordinate;
	}
}