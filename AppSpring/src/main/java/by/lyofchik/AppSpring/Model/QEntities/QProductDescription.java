package by.lyofchik.AppSpring.Model.QEntities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import by.lyofchik.AppSpring.Model.Entities.ProductDescription;
import by.lyofchik.AppSpring.Model.QEntities.QProduct;
import com.querydsl.core.types.dsl.*;


import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductDescription is a Querydsl query type for ProductDescription
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductDescription extends EntityPathBase<ProductDescription> {

    private static final long serialVersionUID = -1827910359L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductDescription productDescription = new QProductDescription("productDescription");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QProduct product;

    public QProductDescription(String variable) {
        this(ProductDescription.class, forVariable(variable), INITS);
    }

    public QProductDescription(Path<? extends ProductDescription> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductDescription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductDescription(PathMetadata metadata, PathInits inits) {
        this(ProductDescription.class, metadata, inits);
    }

    public QProductDescription(Class<? extends ProductDescription> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

