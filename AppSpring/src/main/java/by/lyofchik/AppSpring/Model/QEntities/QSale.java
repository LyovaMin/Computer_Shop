package by.lyofchik.AppSpring.Model.QEntities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import by.lyofchik.AppSpring.Model.QEntities.QUser;
import by.lyofchik.AppSpring.Model.Entities.Sale;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSale is a Querydsl query type for Sale
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSale extends EntityPathBase<Sale> {

    private static final long serialVersionUID = -1629254941L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSale sale = new QSale("sale");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QProduct product;

    public final DatePath<java.time.LocalDate> saleDate = createDate("saleDate", java.time.LocalDate.class);

    public final QUser user;

    public QSale(String variable) {
        this(Sale.class, forVariable(variable), INITS);
    }

    public QSale(Path<? extends Sale> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSale(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSale(PathMetadata metadata, PathInits inits) {
        this(Sale.class, metadata, inits);
    }

    public QSale(Class<? extends Sale> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

