package com.usp.punya.client.navmenu;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.button.image.AboutImageButton;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;

public class MenuViewGwtImpl extends Composite implements MenuView {

	/**
	 * The UiBinder interface.
	 */	
	interface MenuViewGwtImplUiBinder extends
			UiBinder<Widget, MenuViewGwtImpl> {
	}
	
	/**
	 * The UiBinder used to generate the view.
	 */
	private static MenuViewGwtImplUiBinder uiBinder = GWT
			.create(MenuViewGwtImplUiBinder.class);
	
	@UiField
	AboutImageButton aboutButton;
	
	@UiField(provided = true)
	CellList<MenuItem> cellList;
	
	private Presenter presenter;
	
	public MenuViewGwtImpl() {
		
		cellList = new CellList<MenuItem>(new BasicCell<MenuItem>() {

			@Override
			public String getDisplayString(MenuItem model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(MenuItem model) {
				return true;
			}
		});
		
		initWidget(uiBinder.createAndBindUi(this));
		
	}

    @UiHandler("aboutButton")
    protected void onAboutButtonPressed(TapEvent event) {
        if (presenter != null) {
            presenter.onAboutButtonPressed();
        }
    }
	
	@UiHandler("cellList")
	protected void onCellSelected(CellSelectedEvent event) {
		if (presenter != null) {
			int index = event.getIndex();
			presenter.onItemSelected(index);
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void render(List<MenuItem> createTopicsList) {
		cellList.render(createTopicsList);
	}

	@Override
	public void setSelected(int lastIndex, boolean b) {
		cellList.setSelectedIndex(lastIndex, b);
	}
}