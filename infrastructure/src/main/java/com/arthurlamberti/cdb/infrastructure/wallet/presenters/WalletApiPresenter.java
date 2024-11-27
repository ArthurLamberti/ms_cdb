package com.arthurlamberti.cdb.infrastructure.wallet.presenters;

import com.arthurlamberti.cdb.application.paper.retrieve.get.GetPaperOutput;
import com.arthurlamberti.cdb.application.paper.retrieve.list.ListPaperOutput;
import com.arthurlamberti.cdb.application.wallet.retrieve.get.GetWalletOutput;
import com.arthurlamberti.cdb.application.wallet.retrieve.list.ListWalletOutput;
import com.arthurlamberti.cdb.infrastructure.paper.models.GetPaperResponse;
import com.arthurlamberti.cdb.infrastructure.paper.models.ListPaperResponse;
import com.arthurlamberti.cdb.infrastructure.wallet.models.GetWalletResponse;
import com.arthurlamberti.cdb.infrastructure.wallet.models.ListWalletResponse;

public interface WalletApiPresenter {
    static ListWalletResponse present(final ListWalletOutput output) {
        return new ListWalletResponse(
                output.id(),
                output.amount(),
                output.customerId()
        );
    }

    static GetWalletResponse present(final GetWalletOutput output) {
        return new GetWalletResponse(
                output.id(),
                output.amount(),
                output.customerId(),
                output.paperId()
        );
    }
}
