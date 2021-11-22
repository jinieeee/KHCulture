CREATE TABLE "CART" (
	"CART_NO"	NUMBER		NOT NULL,
	"M_NO"	NUMBER		NOT NULL,
	"LR_NO"	NUMBER		NOT NULL,
	"C_STATUS"	VARCHAR2(1)	DEFAULT 'Y'	NOT NULL
);

COMMENT ON COLUMN "CART"."CART_NO" IS '장바구니 번호';

COMMENT ON COLUMN "CART"."M_NO" IS '회원번호';

COMMENT ON COLUMN "CART"."C_STATUS" IS '장바구니 삭제여부';

ALTER TABLE "CART" ADD CONSTRAINT "PK_CART" PRIMARY KEY (
	"CART_NO"
);

ALTER TABLE "CART" ADD CONSTRAINT "FK_MEMBER_TO_CART_1" FOREIGN KEY (
	"M_NO"
)
REFERENCES "MEMBER" (
	"M_NO"
);

ALTER TABLE "CART" ADD CONSTRAINT "FK_LECTURE_REGISTRATION_TO_CART_1" FOREIGN KEY (
	"LR_NO"
)
REFERENCES "LECTURE_REGISTRATION" (
	"LR_NO"
);

alter table cart modify c_status  default 'N';


--select
--*
--from (
--select
--ROWNUM rnum, A.*
--from (
--select 
--		* 
--        from cart
--		join lecture_registration using (lr_no)
--		join lecture using (l_no)
--		join instructor using (instructor_no)
--		where lr_status = 'N' and c_status = 'N'
--        and m_no = 20
--) A)
--where rnum between and ;

select
			*
			from (
			select
			ROWNUM rnum, A.*
			from (
			select 
					* 
			        from cart
					join lecture_registration using (lr_no)
					join lecture using (l_no)
					join instructor using (instructor_no)
					where lr_status = 'N' and c_status = 'N'
			        and m_no = 20
			) A)
			where rnum between 1 and 10;		
