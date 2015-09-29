package com.umi.healthy.data.persist;


import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.umi.healthy.data.*;


public class OfyService
{
	static {
		factory().register(Item.class);
		factory().register(Category.class);
		factory().register(X_CategoryItem.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}