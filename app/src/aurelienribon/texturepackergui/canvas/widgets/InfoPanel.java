package aurelienribon.texturepackergui.canvas.widgets;

import aurelienribon.texturepackergui.canvas.Assets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class InfoPanel extends Container<VerticalGroup> {
    private final Label lblCurrentPage;
    private final Label lblZoom;

    private int pagesAmount, currentPage;

    public InfoPanel(Assets assets) {
        setTouchable(Touchable.disabled);

        setBackground(new TextureRegionDrawable(assets.getWhiteTex()).tint(new Color(0x2a3b56aa)));

        // Labels
        {
            VerticalGroup verticalGroup = new VerticalGroup();
            verticalGroup.align(Align.left);
            verticalGroup.space(0f);

            Label.LabelStyle labelStyle = new Label.LabelStyle(assets.getFont(), Color.WHITE);
            lblCurrentPage = new Label("", labelStyle);
            lblZoom = new Label("", labelStyle);

            verticalGroup.addActor(lblCurrentPage);
            verticalGroup.addActor(lblZoom);

            setActor(verticalGroup);
        }

        pad(4f, 12f, 4f, 12f);

        updatePagesText();
        setZoomLevel(100f);
    }

    public void setZoomLevel(float zoom) {
        lblZoom.setText(String.format("Zoom: %.0f%%", zoom));
    }

    public void setPagesAmount(int pagesAmount) {
        this.pagesAmount = pagesAmount;
        updatePagesText();
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        updatePagesText();
    }

    private void updatePagesText() {
        if (pagesAmount <= 0) {
            lblCurrentPage.setText("No page to show");
        } else {
            lblCurrentPage.setText("Page " + currentPage + " / " + pagesAmount);
        }
    }
}
