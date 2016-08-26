package com.usp.punya.client;

import javax.inject.Inject;

import com.google.gwt.place.shared.PlaceHistoryMapperWithFactory;
import com.google.inject.ImplementedBy;
import com.usp.punya.client.PunyaPlaceHistoryMapper.PunyaPlaceTokenizers;
import com.usp.punya.client.place.SigninPlace;

public interface PunyaPlaceHistoryMapper
    extends PlaceHistoryMapperWithFactory<PunyaPlaceTokenizers> {

	@Inject
	@Override
	public void setFactory(PunyaPlaceTokenizers factory);

	@ImplementedBy(Injector.class)
	public interface PunyaPlaceTokenizers {
		SigninPlace.Tokenizer signinTokenizer();
	}

}