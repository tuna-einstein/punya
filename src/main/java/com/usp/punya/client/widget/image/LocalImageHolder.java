package com.usp.punya.client.widget.image;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.usp.punya.client.widget.image.LocalImageHolder.LocalImageHolderAppearance.Images;

public class LocalImageHolder {
    private static final LocalImageHolderAppearance APPEARANCE = GWT
            .create(LocalImageHolderAppearance.class);
    
    public interface LocalImageHolderAppearance {
        public interface Images {
            ImageResource menu();
        }
        
        Images get();
    }
    
    public static Images get() {
        return APPEARANCE.get();
    }
}
