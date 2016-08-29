package com.usp.punya.client.home;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.usp.punya.client.navmenu.MenuItem;
import com.usp.punya.client.widget.image.MenuImageButton;

public class HomeViewGwtImpl extends Composite implements HomeView {

	/**
	 * The UiBinder interface.
	 */	
	interface HomeViewGwtImplUiBinder extends UiBinder<Widget, HomeViewGwtImpl> {
	}
	
	/**
	 * The UiBinder used to generate the view.
	 */
	private static HomeViewGwtImplUiBinder uiBinder = GWT
			.create(HomeViewGwtImplUiBinder.class);
	
	@UiField
	MenuImageButton menuButton;
	
	@UiField
	ScrollPanel scrollPanel;
	
	@UiField(provided = true)
	CellList<MenuItem> cellList;
	
	private Presenter presenter;

	public HomeViewGwtImpl() {
	    
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

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("menuButton")
	protected void onMenuButtonPressed(TapEvent event) {
	    if (presenter != null) {
	        presenter.onMenuButtonPressed();
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
    public void render(List<MenuItem> createTopicsList) {
        cellList.render(createTopicsList);
    }

    @Override
    public void refresh() {
        scrollPanel.refresh();
    }

}