DELIMITER $
CREATE OR REPLACE PROCEDURE getLogin(in login VARCHAR(255))
BEGIN 
    SELECT * 
    FROM ELEVE
    WHERE ELELOGIN = login;
END $ 
DELIMITER ; 

DELIMITER $

CREATE OR REPLACE PROCEDURE ChiffrerMotDePasse (IN mdp VARCHAR(255))
BEGIN
    DECLARE mdpc VARCHAR(255);

    SET mdpc = PASSWORD(mdp);

    -- Vous pouvez faire ce que vous voulez avec le mot de passe chiffré ici

    SELECT mdpc;
END $

DELIMITER ;
