package pl.epsi.drawlib.gui;

import org.junit.Assert;
import org.junit.Test;
import pl.epsi.drawlib.gui.*;
import pl.epsi.drawlib.gui.widgets.Widget;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class GuiBuilderTest {

    @Test public void buildSimpleForm() {
        final Widget w = GuiBuilder.fromInventoryYaml("test.yaml", "SimpleForm", "src/test/resources/gui")
    }

    @Test public void throwBuildingNonFormTopElement() {
    }

}