import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { FactureAchatDetailComponent } from 'app/entities/facture-achat/facture-achat-detail.component';
import { FactureAchat } from 'app/shared/model/facture-achat.model';

describe('Component Tests', () => {
  describe('FactureAchat Management Detail Component', () => {
    let comp: FactureAchatDetailComponent;
    let fixture: ComponentFixture<FactureAchatDetailComponent>;
    const route = ({ data: of({ factureAchat: new FactureAchat(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [FactureAchatDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FactureAchatDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FactureAchatDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load factureAchat on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.factureAchat).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
